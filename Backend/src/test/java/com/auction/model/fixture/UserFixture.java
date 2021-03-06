package com.auction.model.fixture;

import com.auction.model.Role;
import com.auction.model.User;
import com.auction.model.enums.UserRole;
import com.auction.web.dto.RoleDto;
import com.auction.web.dto.UserDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class UserFixture {

  private static final String email = "test@email.com";
  private static final String firstName = "Nicolae";
  private static final String lastName = "Gherta";
  private static final String password = "secret_123123_31231231";

  public static User user() {
    return User.builder()
            .email(email)
            .firstName(firstName)
            .lastName(lastName)
            .birthday(LocalDate.now())
            .enabled(true)
            .genDate(LocalDateTime.now())
            .password(password)
            .userRating(5D)
            .userRoles(userRole())
            .build();
  }

  public static UserDto userDto() {
    return UserDto.builder()
            .email(email)
            .firstName(firstName)
            .lastName(lastName)
            .birthday(LocalDate.now().toString())
            .enabled(true)
            .userRole(new HashSet<>())
            .build();
  }

  public static Set<Role> userRole() {
    return Set.of(Role.builder()
            .userRole(UserRole.ADMIN)
            .build());
  }

  public static Set<RoleDto> userDtoRole() {
    RoleDto roleDto = new RoleDto();
    roleDto.setName("ADMIN");
    return Set.of(roleDto);
  }

  public static User disabledUser() {
    User user = user();
    user.setEnabled(false);
    return user;
  }

}
