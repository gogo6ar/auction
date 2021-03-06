package com.auction.web.contoller;

import com.auction.service.interfaces.ResetPasswordService;
import com.auction.service.interfaces.UserService;
import com.auction.service.interfaces.WithdrawMoneyService;
import com.auction.web.dto.UserDto;
import com.auction.web.dto.request.AddDefaultAddressRequest;
import com.auction.web.dto.request.ChangePasswordRequest;
import com.auction.web.dto.request.UpdatePasswordRequest;
import com.auction.web.dto.request.UserUpdateRequest;
import com.auction.web.dto.request.WithdrawMoneyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.io.UnsupportedEncodingException;

//@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
class UserController {
  private final ResetPasswordService resetPasswordService;
  private final UserService userService;
  private final WithdrawMoneyService withdrawMoneyService;

  @PostMapping("/reset/password/{email}")
  public ResponseEntity<Void> resetPasswordByEmail(@Valid
                                                   @PathVariable
                                                   @Email String email)
          throws MessagingException, UnsupportedEncodingException {
    resetPasswordService.resetPasswordByEmail(email);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/password")
  public ResponseEntity<Void> updatePassword(@Valid @RequestBody UpdatePasswordRequest request) {
    userService.updatePassword(request);
    return ResponseEntity.ok().build();
  }

  @PutMapping
  public ResponseEntity<UserDto> update(@Valid @RequestBody UserUpdateRequest request) {
    return ResponseEntity.ok(userService.update(request));
  }

  @PostMapping("/update/password/{code}")
  public ResponseEntity<Void> disableAndSetNewPassword(@Valid @RequestBody ChangePasswordRequest request,
                                                       @PathVariable String code) {
    resetPasswordService.changePasswordAfterReset(request, code);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/disable/code/{code}")
  public ResponseEntity<Void> disableAndVerify(@PathVariable String code) {
    resetPasswordService.verifyAndDisableUser(code);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/disable/{userId}")
  public ResponseEntity<UserDto> disable(@PathVariable("userId") Long userId) {
    return ResponseEntity.ok(userService.disable(userId));
  }

  @PostMapping("/enable/{userId}")
  public ResponseEntity<UserDto> enable(@PathVariable("userId") Long userId) {
    return ResponseEntity.ok(userService.enable(userId));
  }

  @GetMapping
  public ResponseEntity<Page<UserDto>> getAllUsers(@RequestParam(defaultValue = "1") int page,
                                                   @RequestParam(defaultValue = "10") int perPage) {
    return ResponseEntity.ok().body(userService.get(page, perPage));
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
    return ResponseEntity.ok(userService.getById(userId));
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
    userService.deleteById(userId);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/tutorial/{userId}")
  public ResponseEntity<Void> checkedTutorial(@PathVariable Long userId) {
    userService.checkTutorial(userId);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/address")
  public ResponseEntity<Void> addDefaultAddress(@RequestBody AddDefaultAddressRequest request) {
    userService.addDefaultAddress(request);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/withdraw")
  public ResponseEntity<Void> withdrawMoney(@RequestBody WithdrawMoneyRequest request) {
    withdrawMoneyService.create(request);
    return ResponseEntity.ok().build();
  }
}
