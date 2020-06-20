package com.digimenu.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.digimenu.main.domain.converter.*;
import com.digimenu.main.domain.dto.*;
import com.digimenu.main.domain.entity.*;
import com.digimenu.main.domain.request.*;
import com.digimenu.main.domain.response.ReportResponse;
import com.digimenu.main.domain.response.TableNameResponse;
import com.digimenu.main.service.*;
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

@Controller
@RequestMapping(path = "/restaurant")
public class RestaurantController {

    private RestaurantService restaurantService;
    private MenuService menuService;
    private CartService cartService;
    private CategoryService categoryService;
    private Table_OrdersService tableOrdersService;
    private WebsocketMessageService websocketMessageService;
    private SimpMessagingTemplate simpMessagingTemplate;
    private CampaignService campaignService;
    private CommentService commentService;
    private CloudinaryService cloudinaryService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, MenuService menuService, CartService cartService, CategoryService categoryService, Table_OrdersService tableOrdersService, WebsocketMessageService websocketMessageService, SimpMessagingTemplate simpMessagingTemplate, CampaignService campaignService, CommentService commentService, CloudinaryService cloudinaryService) {
        this.restaurantService = restaurantService;
        this.menuService = menuService;
        this.cartService = cartService;
        this.categoryService = categoryService;
        this.tableOrdersService = tableOrdersService;
        this.websocketMessageService = websocketMessageService;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.campaignService = campaignService;
        this.commentService = commentService;
        this.cloudinaryService = cloudinaryService;
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView getlogin(Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("restaurantlogin");
        return mav;
    }

    @PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @GetMapping("/additem")
    public String addItemGet(PanelMenuDto menu, Model model) {
        model.addAttribute("category", categoryService.getCategories());
        return "addmenuitem";
    }

    @PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @PostMapping("/additem")
    public String addItemPost(@ModelAttribute(value = "panelMenuDto") @Valid PanelMenuDto panelMenuDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.err.println(result.getFieldErrors());
            model.addAttribute("category", categoryService.getCategories());
            return "addmenuitem";
        }
        this.menuService.saveMenuItem(panelMenuDto);
        return "redirect:/restaurant/menu";
    }

    @PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @GetMapping("/edititem/{id}")
    public String editMenu(Model model, @PathVariable("id") Long id) {
        model.addAttribute("category", categoryService.getCategories());
        Menu menu = menuService.getMenuItem(id);
        model.addAttribute("imagePublicId", menu.getImagePublicId()); // mevcut fotoyu cdn'den çekip görüntülemek için
        model.addAttribute("panelMenuDto", PanelMenuDtoConverter.convert(menu));
        return "editmenuitem";
    }

    @PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @PostMapping("/updateitem")
    public String editItem(@ModelAttribute(value = "panelMenuDto") @Valid PanelMenuDto panelMenuDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("category", categoryService.getCategories());
            model.addAttribute("panelMenuDto", panelMenuDto);
            return "editmenuitem";
        }
        menuService.updateMenuItem(panelMenuDto);
        return "redirect:/restaurant/menu";
    }

    @PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @GetMapping("/tables")
    public String getTables(Model model) {
        Restaurant restaurant = restaurantService.getLoggedInRestaurant();
        List<TableNameResponse> tableNames = restaurantService.getTableNames(restaurant);
        model.addAttribute("tables", tableNames);
        model.addAttribute("tableAmount", restaurant.getTableAmount());
        return "showtable";
    }

    @PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @GetMapping("/tableNaming")
    public String getTableNaming(Model model) {
        Restaurant restaurant = restaurantService.getLoggedInRestaurant();
        TableNameRequest request = restaurantService.getTableNameRequest(restaurant);
        if (request.getRequestItemList().isEmpty()) {
            List<TableNameRequestItem> list = new ArrayList<>();
            for (int i = 0; i < restaurant.getTableAmount(); i++) {
                list.add(new TableNameRequestItem());
            }
            request.setRequestItemList(list);
        }
        model.addAttribute("tableNameRequest", request);
        return "tablenaming";
    }

    @PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @PostMapping("/tableNaming")
    public String postTableNaming(@ModelAttribute(value = "tableNameRequest") TableNameRequest tableNameRequest) {
        restaurantService.saveTableNames(TableNameDtoConverter.convert(tableNameRequest, restaurantService.getLoggedInRestaurant()));
        return "redirect:/restaurant/tables";
    }

    @PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @GetMapping("/menu")
    public String getMenu(Model model) {
        Restaurant restaurant = restaurantService.getLoggedInRestaurant();
        model.addAttribute("menu", menuService.getAllItemsByRestaurant(restaurant.getId()));
        return "showmenu";
    }


    @PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") Long id) {
        try {
            menuService.deleteMenuItem(menuService.getMenuItem(id));
        } catch (Exception e) {
            System.err.println(e);
        }

        return "redirect:/restaurant/menu";
    }

    @PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @GetMapping("/cart/{masa}")
    public String getCart(Model model, @PathVariable("masa") Integer masaNo) {
        Restaurant res = restaurantService.getLoggedInRestaurant();
        try {
            List<Cart> siparisler = cartService.getCart(res.getId(), masaNo);
            Optional<TableName> tableName = restaurantService.getTableName(res, masaNo);
            if (tableName.isPresent()) {
                String masaIsmi = tableName.get().getName();
                model.addAttribute("masaIsmi", masaIsmi);
            }
            model.addAttribute("masaNo", masaNo);
            model.addAttribute("orders", siparisler);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "showcart";
    }

    @PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @GetMapping("/flushcart/{masa}")
    public String freeCart(@PathVariable("masa") Integer id) {
        Restaurant res = restaurantService.getLoggedInRestaurant();
        try {
            cartService.emptyCart(res.getId(), id);
        } catch (Exception e) {
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
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @ResponseBody
    //@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @GetMapping("/delete-wrong-order/{name}/{masaNo}/{cartId}")
    public ResponseEntity<String> deleteWrongOrder(@PathVariable("name") String name, @PathVariable("masaNo") Integer masaNo, @PathVariable("cartId") Long cartId) {
        try {
            tableOrdersService.deleteWrongTableOrder(restaurantService.getLoggedInRestaurant(), name, masaNo);
            cartService.deleteCart(cartId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @GetMapping("/transferCart")
    public String transferCartGet(TransferCartRequest transferCartRequest, Model model) {
        model.addAttribute("tables", restaurantService.getLoggedInRestaurant().getTableAmount());
        return "transfercart";
    }


    @PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @PostMapping("/transferCart")
    public String transferCartPost(@ModelAttribute("transferCartRequest") TransferCartRequest transferCartRequest, BindingResult bindingResult, Model model) {
        TransferCartDto dto = TransferCartConverter.convert(transferCartRequest);
        dto.setId(restaurantService.getLoggedInRestaurant().getId());// converterda gerçekleştir bu işlemi
        Optional<List<Cart>> result = cartService.transferCart(dto);
        if (result.isPresent()) {
            return "redirect:/restaurant/tables";
        } else {
            model.addAttribute("error", "Bir hata oluştu ve transfer yapılamadı.");
            return "redirect:/restaurant/transferCart";
        }
    }

    @PostMapping("/delivery")
    public ResponseEntity<String> setDeliveryOption(@RequestBody DeliveryRequest request) {
        int result = cartService.updateDeliveryOption(request.getValue(), request.getId());
        if (result != 1) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @PostMapping("/report")
    @ResponseBody
    public ReportResponse sellReport(@RequestBody ReportRequest request) {
        return tableOrdersService.getReport(request.getStartDate(), request.getEndDate());
    }

    @PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @GetMapping("/report")
    public String getReportPage() {
        return "getreport";
    }

    @CrossOrigin
    @GetMapping("/mesaj/{id}")
    @ResponseBody
    public void deleteMessageEntity(@PathVariable("id") Integer id) {
        websocketMessageService.deleteMessage(id);
    }

    @CrossOrigin
    @GetMapping("/recallMessages")
    @ResponseBody
    public void returnUnsendMessages() {
        String username = restaurantService.getLoggedInRestaurantUsername();
        List<WebsocketMessage> messages = websocketMessageService.getAllMessages(restaurantService.getLoggedInRestaurant().getId());
        for (WebsocketMessage message : messages) {
            System.out.println(message.toString());
            this.simpMessagingTemplate.convertAndSendToUser(username, "/restaurant/message", MessageDtoConverter.convert(message));
        }
    }

    @GetMapping("/passivesAndFavourites")
    public String getPassives(Model model) {
        List<Menu> passives = menuService.getPassiveItemsByRestaurant(restaurantService.getLoggedInRestaurant().getId());
        List<Menu> favourites = menuService.getFavoriteItemsByRestaurant(restaurantService.getLoggedInRestaurant().getId());
        model.addAttribute("passives", passives);
        model.addAttribute("favourites", favourites);
        return "passivespage";
    }

    @GetMapping("/seeCampaigns")
    public String getCampaigns(Model model) {
        List<Campaign> campaigns = campaignService.getAllCampaignsByRestaurant(restaurantService.getLoggedInRestaurant());
        model.addAttribute("campaigns", campaigns);
        return "showcampaigns";
    }

    @GetMapping("/createCampaign")
    public String getCreateCampaign(PanelCampaignDto campaign, Model model) {
        return "createcampaign";
    }

    @PostMapping("/createCampaign")
    public String postCreateCampaign(@ModelAttribute(value = "panelCampaignDto") @Valid PanelCampaignDto panelCampaignDto, BindingResult result) {
        if (result.hasErrors()) {
            return "createcampaign";
        } else {
            campaignService.addCampaign(panelCampaignDto);
            return "redirect:/restaurant/seeCampaigns";
        }
    }

    @GetMapping("/updateCampaign/{id}")
    public String getUpdateCampaign(@PathVariable("id") Long id, Model model) {
        Campaign campaign = campaignService.getCampaign(id);
        model.addAttribute("imagePublicId", campaign.getImagePublicId());
        model.addAttribute("panelCampaignDto", PanelCampaignConverter.convert(campaign));
        return "updatecampaign";
    }

    @PostMapping("/updateCampaign")
    //parametrelerin sırası önemli burda! bozarsan çalışmıyor
    public String postUpdateCampaign(@ModelAttribute(value = "panelCampaignDto") @Valid PanelCampaignDto panelCampaignDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("panelCampaignDto", panelCampaignDto);
            return "updatecampaign";
        }
        campaignService.updateCampaign(panelCampaignDto);
        return "redirect:/restaurant/seeCampaigns";
    }

    @GetMapping("/deleteCampaign/{id}")
    public String deleteCampaign(@PathVariable(value = "id") Long id) {
        campaignService.deleteCampaign(id);
        return "redirect:/restaurant/seeCampaigns";
    }

    @PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @GetMapping("/comments")
    public String getComments(Model model) {
        model.addAttribute("comments", commentService.getRestaurantComments());
        return "listcommentpage";
    }

    @PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @GetMapping("/logo")
    public String getLogoPage(LogoDto image,Model model) {
        model.addAttribute("logoId",restaurantService.getLoggedInRestaurant().getLogoPublicId());
        return "logopage";
    }


    @PostMapping("/logo")
    @PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    public String postLogoPage(@ModelAttribute("logoDto") LogoDto dto, Model model) {
        restaurantService.saveRestaurantLogo(dto);
        return "redirect:/restaurant/menu";
    }
    @GetMapping("/theme")
    @PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    public String getThemePage(Model model){
        Restaurant restaurant = restaurantService.getLoggedInRestaurant();
        ThemeDto dto= new ThemeDto();
        if(restaurant.getThemeId()!=null){
            dto.setThemeId(restaurant.getThemeId());
        }
        model.addAttribute("themeDto",dto);
        return "themepage";
    }

    @PostMapping("/theme")
    @PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
    public String postThemePage(@ModelAttribute("themeDto") ThemeDto themeDto,Model model){
        Restaurant restaurant=restaurantService.getLoggedInRestaurant();
        restaurant.setThemeId(themeDto.getThemeId());
        restaurantService.saveRestaurant(restaurant);
        return "redirect:/restaurant/menu";
    }

}
