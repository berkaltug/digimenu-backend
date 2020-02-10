package com.digimenu.main.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.digimenu.main.domain.converter.MessageDtoConverter;
import com.digimenu.main.domain.converter.TransferCartConverter;
import com.digimenu.main.domain.dto.TransferCartDto;
import com.digimenu.main.domain.entity.WebsocketMessage;
import com.digimenu.main.domain.request.DeliveryRequest;
import com.digimenu.main.domain.request.ReportRequest;
import com.digimenu.main.domain.request.TransferCartRequest;
import com.digimenu.main.domain.response.ReportResponse;
import com.digimenu.main.service.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.digimenu.main.domain.entity.Cart;
import com.digimenu.main.domain.entity.Menu;
import com.digimenu.main.domain.entity.Restaurant;

@Controller
@RequestMapping(value="/restaurant")
public class RestaurantController {
	
	private RestaurantService restaurantService;
	private MenuService menuService;
	private CartService cartService;
	private CategoryService categoryService;
	private Table_OrdersService tableOrdersService;
	private WebsocketMessageService websocketMessageService;
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	public RestaurantController(RestaurantService restaurantService, MenuService menuService, CartService cartService, CategoryService categoryService, Table_OrdersService tableOrdersService, WebsocketMessageService websocketMessageService, SimpMessagingTemplate simpMessagingTemplate) {
		this.restaurantService = restaurantService;
		this.menuService = menuService;
		this.cartService = cartService;
		this.categoryService = categoryService;
		this.tableOrdersService = tableOrdersService;
		this.websocketMessageService = websocketMessageService;
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	@GetMapping("/login")
	public ModelAndView getlogin(Model model) {
		ModelAndView mav = new ModelAndView();
	    mav.setViewName("restaurantlogin");
	    return mav;
	}
	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@GetMapping("/additem")
	public String addItemGet(Menu menu,Model model) {
		model.addAttribute("category", categoryService.getCategories());
		return "addmenuitem";
	}
	
	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@PostMapping("/additem")
	public String addItemPost(@ModelAttribute(value="menu") @Valid Menu menu,Model model,BindingResult bindingResult) {
		System.out.println(menu);
		if (bindingResult.hasErrors()) {
			return "addmenuitem";
		}
		menu.setRestaurant(restaurantService.getLoggedInRestaurant());
		this.menuService.saveMenuItem(menu);
		return "redirect:/restaurant/menu";
	}
	
	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@GetMapping("/tables")
	public String getTables(Model model) {
		Restaurant restaurant=restaurantService.getLoggedInRestaurant();
		model.addAttribute("tables",restaurant.getTableAmount());
		return "showtable";
	}
	
	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@GetMapping("/menu")
	public String getMenu(Model model) {
		Restaurant restaurant=restaurantService.getLoggedInRestaurant();

		model.addAttribute("menu",menuService.getMenuItemsByRestaurant(restaurant.getId()).getItems());
		return "showmenu";
	}
	
	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@GetMapping("/edititem/{id}")
	public String editMenu(Model model,@PathVariable("id") Long id) {
		model.addAttribute("category",categoryService.getCategories());
		model.addAttribute("menu",menuService.getMenuItem(id));
		System.out.println(menuService.getMenuItem(id));
		return "editmenuitem";
	}
	
	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@PostMapping("/updateitem")
	public String editItem(@ModelAttribute(value="menu") @Valid Menu menu) {
		menuService.updateMenuItem(menu);
		return "redirect:/restaurant/menu";
	}


	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@GetMapping("/delete/{id}")
	public String deleteItem(@PathVariable("id") Long id) {
		try {
			menuService.deleteMenuItem(menuService.getMenuItem(id));
		}catch(Exception e) {
			System.err.println(e);
		}
		
		return "redirect:/restaurant/menu";
	}
	
	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@GetMapping("/cart/{masa}")
	public String getCart(Model model,@PathVariable("masa") Integer masaNo) {
		Restaurant res=restaurantService.getLoggedInRestaurant();
		try {
			List<Cart> siparisler=cartService.getCart(res.getId(), masaNo);
			model.addAttribute("masaNo",masaNo);
			model.addAttribute("orders",siparisler);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "showcart";
	}
	
	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@GetMapping("/flushcart/{masa}")
	public String freeCart(@PathVariable("masa") Integer id) {
		Restaurant res=restaurantService.getLoggedInRestaurant();
		try {
			cartService.emptyCart(res.getId(),id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/restaurant/tables";
	}

	@CrossOrigin
	@ResponseBody
	//@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@GetMapping("/flushitem/{id}")
	public ResponseEntity<String> freeCartItem(@PathVariable("id") Long id) {
		try {
			cartService.deleteCart(id);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@CrossOrigin
	@ResponseBody
	//@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@GetMapping("/delete-wrong-order/{name}/{masaNo}/{cartId}")
	public ResponseEntity<String> deleteWrongOrder(@PathVariable("name") String name,@PathVariable("masaNo") Integer masaNo,@PathVariable("cartId") Long cartId) {
		try {
			tableOrdersService.deleteWrongTableOrder(restaurantService.getLoggedInRestaurant(),name,masaNo);
			cartService.deleteCart(cartId);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@GetMapping ("/transferCart")
	public String transferCartGet(TransferCartRequest transferCartRequest,Model model){
		model.addAttribute("tables",restaurantService.getLoggedInRestaurant().getTableAmount());
		return "transfercart";
	}


	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@PostMapping ("/transferCart")
	public String transferCartPost(@ModelAttribute("transferCartRequest") TransferCartRequest transferCartRequest,BindingResult bindingResult,Model model){
		TransferCartDto dto = TransferCartConverter.convert(transferCartRequest);
		dto.setId(restaurantService.getLoggedInRestaurant().getId());// converterda gerçekleştir bu işlemi
		Optional<List<Cart>> result = cartService.transferCart(dto);
		if(result.isPresent()){
			return "redirect:/restaurant/tables";
		}
		else{
			model.addAttribute("error","Bir hata oluştu ve transfer yapılamadı.");
			return "redirect:/restaurant/transferCart";
		}
	}

	@PostMapping("/delivery")
	public ResponseEntity<String> setDeliveryOption(@RequestBody DeliveryRequest request){
		int result = cartService.updateDeliveryOption(request.getValue(), request.getId());
		if(result != 1){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@PostMapping("/report")
	@ResponseBody
	public ReportResponse sellReport(@RequestBody ReportRequest request){
		return tableOrdersService.getReport(request.getStartDate(),request.getEndDate());
	}

	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@GetMapping("/report")
	public String getReportPage(){
		return "getreport";
	}

	@CrossOrigin
	@GetMapping("/mesaj/{id}")
	@ResponseBody
	public void deleteMessageEntity(@PathVariable("id") Integer id){
		websocketMessageService.deleteMessage(id);
	}

	@CrossOrigin
	@GetMapping("/recallMessages")
	@ResponseBody
	public void  returnUnsendMessages(){
		String username=restaurantService.getLoggedInRestaurantUsername();
		List<WebsocketMessage> messages = websocketMessageService.getAllMessages(restaurantService.getLoggedInRestaurant().getId());
		for (WebsocketMessage message : messages) {
			System.out.println(message.toString());
			this.simpMessagingTemplate.convertAndSendToUser(username, "/restaurant/message", MessageDtoConverter.convert(message));
		}
	}

}
