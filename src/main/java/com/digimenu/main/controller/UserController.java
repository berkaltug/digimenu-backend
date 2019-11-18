package com.digimenu.main.controller;

import java.util.Arrays;
import java.util.UUID;

import javax.validation.Valid;

import com.digimenu.main.service.SecurityService;
import com.digimenu.main.service.SendGridMailService;
import com.sendgrid.helpers.mail.objects.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digimenu.main.repository.ConfirmationTokenRepository;
import com.digimenu.main.repository.PasswordResetTokenRepository;
import com.digimenu.main.security.ConfirmationToken;
import com.digimenu.main.security.PasswordResetToken;
import com.digimenu.main.security.User;
import com.digimenu.main.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {

	private UserService userService;
	private ConfirmationTokenRepository confirmTokenRepo;
	private SendGridMailService sendGridMailService;
	private PasswordResetTokenRepository passwordResetTokenRepository;
	private SecurityService securityService;

	@Autowired
	public UserController(UserService userService, ConfirmationTokenRepository confirmTokenRepo, SendGridMailService sendGridMailService, PasswordResetTokenRepository passwordResetTokenRepository, SecurityService securityService) {
		this.userService = userService;
		this.confirmTokenRepo = confirmTokenRepo;
		this.sendGridMailService = sendGridMailService;
		this.passwordResetTokenRepository = passwordResetTokenRepository;
		this.securityService = securityService;
	}

	@PostMapping("/register")
	@ResponseBody
	ResponseEntity<String> registerUser(@Valid @RequestBody User user, BindingResult bindingResult) {
		
		User existingUser= userService.findByEmail(user.getEmail());
		User existingUsername=userService.findByUsername(user.getUsername());
		try {
			if (existingUser != null) {
				return new ResponseEntity<>("Bu mail adresine kayıtlı kullanıcı bulunmaktadır", HttpStatus.CONFLICT);
			} else if (existingUsername != null) {
				return new ResponseEntity<>("Bu kullanıcı adı kullanılmaktadır", HttpStatus.CONFLICT);
			}else if(bindingResult.hasErrors()){
				return new ResponseEntity<>(bindingResult.getFieldErrors().get(0).getDefaultMessage(),HttpStatus.BAD_REQUEST);
			}
			else {
				userService.save(user);
				ConfirmationToken confirmationToken = new ConfirmationToken(user);
				confirmTokenRepo.save(confirmationToken);
				StringBuilder sb = new StringBuilder();
				String mailContent = sb.append("Üyeliğinizi doğrulamak için lütfen doğrulama linkine tıklayınız : '\n'")
						.append("https://digimenu.herokuapp.com/user/confirmaccount/").append(confirmationToken.getConfirmationToken())
						.append('\n')
						.append("Digimenu Ekibi").toString();
				sendGridMailService.sendEmail("digimenuinfo@gmail.com", user.getEmail(), "Digimenu'ye Hoşgeldiniz !", new Content("text/plain", mailContent));
			}
		}catch(Exception ex){
			return new ResponseEntity<>(ex.getCause().getMessage(),HttpStatus.BAD_REQUEST);
			}
		return new ResponseEntity<>("Aktivasyon epostasi adresinize gönderilmiştir",HttpStatus.CREATED);
	}
	
	@GetMapping("/confirmaccount/{token}")
	@ResponseBody
		ResponseEntity<String >confirmAccount(@PathVariable("token") String confirmToken) throws Exception {
		ConfirmationToken token=confirmTokenRepo.findByConfirmationToken(confirmToken);
		if(token!=null) {
			User user = userService.findByEmail(token.getUser().getEmail());
            user.setEnabled(true);
            userService.save(user);  
            confirmTokenRepo.delete(token); // onaylanınca tokenı siliyoruz
		}
		else {
			return new ResponseEntity<>("Üyelik onaylanırken bir hata oluştu !",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("üyelik onaylanmıştır",HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/login")
	ResponseEntity<String> login(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		return new ResponseEntity<>("Başarılı",HttpStatus.ACCEPTED);
	}

	//burası sendgride göre yazılıp düzenlenicek
	@PostMapping("/forgetpassword/{email}")
	@ResponseBody
	ResponseEntity<String> forgetPassword(@PathVariable("email") String email){
		
		User user=userService.findByEmail(email);
		if(user==null) {
			return new ResponseEntity<>("Böyle bir epostaya kayıtlı kullanıcı bulunmamıştır",HttpStatus.NOT_FOUND);
		}

		PasswordResetToken prt=new PasswordResetToken(user);
		passwordResetTokenRepository.save(prt);
		StringBuilder sb=new StringBuilder();
		String contentBody=sb.append("Parolanızı yenilemek için lütfen linke tıklayınız : " +"\n"
				+"https://digimenu.herokuapp.com/user/resetpassword/"+prt.getToken() +"\n"
				+"Eğer bu eposta bilginiz dahilinde gelmediyse , lütfen tıklamayıp görmezden geliniz ! "+"\n"
				+"Digimenu Ekibi")
				.toString();
		sendGridMailService.sendEmail("digimenuinfo@gmail.com",user.getEmail(),"Digimenu Parola Yenileme",new Content("text/plain",contentBody));

		return new ResponseEntity<>("Parola resetleme linkiniz epostanıza gönderilmiştir",HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/resetpassword/{token}")
	String resetPasswordPage(Model model,@PathVariable("token") String token) {
		
		PasswordResetToken prt=passwordResetTokenRepository.findByToken(token);
		if(prt==null) {
			model.addAttribute("error", "Bir sorun oluştu,lütfen yeniden deneyiniz.");
		}
		User user=prt.getUser();
		System.err.println("reset token userı !!!! -----------> " + user.toString() );

		Authentication auth = new UsernamePasswordAuthenticationToken(
			      user, null, Arrays.asList(
			      new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
		SecurityContextHolder.getContext().setAuthentication(auth);			//sadece şifre yenileme sırasında bu yetkiyi veriyoruz
		return "forgetpassword";	    
	}
	
	//hatalı
	@PostMapping("/savepassword")
	public String resetPassword(@ModelAttribute("pass") String pass) throws Exception {
		User user=userService.findByUsername(securityService.findLoggedInUsername());
		user.setPassword(pass);
		userService.save(user);
		return "resetpasswordsuccess";
	}
}
