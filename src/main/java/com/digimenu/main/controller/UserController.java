package com.digimenu.main.controller;

import java.util.Arrays;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.digimenu.main.repository.ConfirmationTokenRepository;
import com.digimenu.main.repository.PasswordResetTokenRepository;
import com.digimenu.main.repository.RoleRepository;
import com.digimenu.main.repository.UserRepository;
import com.digimenu.main.security.ConfirmationToken;
import com.digimenu.main.security.PasswordResetToken;
import com.digimenu.main.security.User;
import com.digimenu.main.service.EmailSenderService;
import com.digimenu.main.service.SecurityService;
import com.digimenu.main.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ConfirmationTokenRepository confirmTokenRepo;
	@Autowired
    private EmailSenderService emailSenderService;
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@PostMapping("/register")
	@ResponseBody
	ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
		
		User existingUser= userService.findByEmail(user.getEmail());
		User existingUsername=userService.findByUsername(user.getUsername());
		
		if(existingUser!=null) {
			return new ResponseEntity<>("Bu mail adresine kayıtlı kullanıcı bulunmaktadır",HttpStatus.CONFLICT);
		}
		else if(existingUsername!=null) {
			return new ResponseEntity<>("Bu kullanıcı adı kullanılmaktadır",HttpStatus.CONFLICT);
		}
		else {
			try {
				userService.save(user);
				ConfirmationToken confirmationToken = new ConfirmationToken(user);

				confirmTokenRepo.save(confirmationToken);

				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(user.getEmail());
				mailMessage.setSubject("Digimenu'ye Hoşgeldiniz!");
//            mailMessage.setFrom("business@digimenu.online"); //gereksiz 	
				mailMessage.setText("Üyeliğinizi doğrulamak için lütfen doğrulama linkine tıklayınız : " + "\n"
						+ "https://digimenu.herokuapp.com/user/confirmaccount/" + confirmationToken.getConfirmationToken() + "\n"
						+ "Digimenu Ekibi");

				emailSenderService.sendEmail(mailMessage);
			}catch(Exception e){
				System.err.println(e.getMessage());
			}
		}
		
		return new ResponseEntity<>("Aktivasyon epostasi adresinize gönderilmiştir",HttpStatus.CREATED);
	}
	
	@GetMapping("/confirmaccount/{token}")
	@ResponseBody
		ResponseEntity<String >confirmAccount(@PathVariable("token") String confirmToken) {
		ConfirmationToken token=confirmTokenRepo.findByConfirmationToken(confirmToken);
		if(token!=null) {
			User user = userService.findByEmail(token.getUser().getEmail());
            user.setEnabled(true);
            userService.save(user);  
            confirmTokenRepo.delete(token); // onaylanınca tokenı siliyoruz
		}
		else {
		}
		
		return new ResponseEntity<>("üyelik onaylanmıştır",HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/login")
	ResponseEntity<String> login(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		return new ResponseEntity<>("Başarılı",HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/forgetpassword/{email}")
	@ResponseBody
	ResponseEntity<String> forgetPassword(@PathVariable("email") String email){
		
		User user=userService.findByEmail(email);
		if(user==null) {
			return new ResponseEntity<>("Böyle bir epostaya kayıtlı kullanıcı bulunmamıştır",HttpStatus.NOT_FOUND);
		}

		PasswordResetToken prt=new PasswordResetToken(user);
		passwordResetTokenRepository.save(prt);
		SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Digimenu Parola Yenileme");
        mailMessage.setText("Parolanızı yenilemek için lütfen linke tıklayınız : " +"\n"
        +"https://digimenu.herokuapp.com/user/resetpassword/"+prt.getToken() +"\n"
        +"Eğer bu eposta bilginiz dahilinde gelmediyse , lütfen tıklamayıp görmezden geliniz ! "+"\n"
        +"Digimenu Ekibi");
        emailSenderService.sendEmail(mailMessage);
		return new ResponseEntity<>("Parola resetleme linkiniz epostanıza gönderilmiştir",HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/resetpassword/{token}")
	String resetPasswordPage(Model model,@PathVariable("token") String token) {
		
		PasswordResetToken prt=passwordResetTokenRepository.findByToken(token);
		if(prt==null) {
			model.addAttribute("error", "Bir sorun oluştu,lütfen yeniden deneyiniz.");
		}
		User user=prt.getUser();
		Authentication auth = new UsernamePasswordAuthenticationToken(
			      user, null, Arrays.asList(
			      new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
		SecurityContextHolder.getContext().setAuthentication(auth);			//sadece şifre yenileme sırasında bu yetkiyi veriyoruz
		return "forgetpassword";	    
	}
	
	//hatalı
	@PostMapping("/savepassword")
	public String resetPassword(@ModelAttribute("pass") String pass){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		user.setPassword(pass);
		userService.save(user);
		return "resetpasswordsuccess";
	}
}
