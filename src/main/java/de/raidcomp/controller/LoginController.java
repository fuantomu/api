package de.raidcomp.controller;

import java.util.Optional;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.validation.Valid;
import de.raidcomp.controller.dto.LoginDto;
import de.raidcomp.controller.dto.save.SaveLoginDto;
import de.raidcomp.data.entity.LoginEntity;
import de.raidcomp.data.repository.LoginRepository;

@Controller("/login/")
public class LoginController {

  protected final LoginRepository loginRepository;

  public LoginController(LoginRepository loginRepository) {
    this.loginRepository = loginRepository;
  }

  @Get("/{host}")
  public LoginDto getLoginAge(String host) {
    Optional<LoginEntity> userLogin = loginRepository.findById(host);
    if (!userLogin.isEmpty()) {
      return new LoginDto(userLogin.get().getCreated_date(), userLogin.get().getRole(), userLogin.get().getUsername());
    }
    return new LoginDto((long) 0, -1, "");
  }

  @Post("/{host}")
  public void createLoginAge(String host, @Valid @Body SaveLoginDto body) {
    LoginEntity userLogin = new LoginEntity();

    userLogin.setHost(host);
    userLogin.setCreated_date(System.currentTimeMillis());
    userLogin.setRole(body.role());
    userLogin.setUsername(body.username());

    Optional<LoginEntity> existingLogin = loginRepository.findById(host);
    if (existingLogin.isEmpty()) {
      loginRepository.save(userLogin);
    }
    loginRepository.update(userLogin);
  }

  @Post("/delete/{host}")
  public void deleteLoginAge(String host) {
    Optional<LoginEntity> userLogin = loginRepository.findById(host);
    if (!userLogin.isEmpty()) {
      loginRepository.delete(userLogin.get());
    }
  }
}
