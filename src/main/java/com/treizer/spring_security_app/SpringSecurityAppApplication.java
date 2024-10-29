package com.treizer.spring_security_app;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.treizer.spring_security_app.persistence.entities.Permission;
import com.treizer.spring_security_app.persistence.entities.Role;
import com.treizer.spring_security_app.persistence.entities.RoleEnum;
import com.treizer.spring_security_app.persistence.entities.User;
import com.treizer.spring_security_app.persistence.repositories.IUserRepository;

@SpringBootApplication
public class SpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);
	}

	@Bean
	CommandLineRunner init(IUserRepository userRepository) {
		return args -> {
			/* CREATE PERMISSIONS */
			Permission createPermission = Permission.builder()
					.name("CREATE")
					.build();

			Permission readPermission = Permission.builder()
					.name("READ")
					.build();

			Permission updatePermission = Permission.builder()
					.name("UPDATE")
					.build();

			Permission deletePermission = Permission.builder()
					.name("DELETE")
					.build();

			Permission refactorPermission = Permission.builder()
					.name("REFACTOR")
					.build();

			/* Create ROLES */
			Role roleAdmin = Role.builder()
					.roleName(RoleEnum.ADMIN)
					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			Role roleUser = Role.builder()
					.roleName(RoleEnum.USER)
					.permissions(Set.of(createPermission, readPermission))
					.build();

			Role roleInvited = Role.builder()
					.roleName(RoleEnum.INVITED)
					.permissions(Set.of(readPermission))
					.build();

			Role roleDeveloper = Role.builder()
					.roleName(RoleEnum.DEVELOPER)
					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission,
							refactorPermission))
					.build();

			/* Create USERS */
			User userHugo = User.builder()
					.username("hugo")
					// .password("1234")
					.password("$2a$10$vVgxw0q/lOhcEYKQrr.VbOtBkIU8j8HJNwFpzkhJf5NH3EgGd9QIe")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			User userPaco = User.builder()
					.username("paco")
					// .password("1234")
					.password("$2a$10$vVgxw0q/lOhcEYKQrr.VbOtBkIU8j8HJNwFpzkhJf5NH3EgGd9QIe")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			User userLuis = User.builder()
					.username("luis")
					// .password("1234")
					.password("$2a$10$vVgxw0q/lOhcEYKQrr.VbOtBkIU8j8HJNwFpzkhJf5NH3EgGd9QIe")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleInvited))
					.build();

			User userAbril = User.builder()
					.username("abril")
					// .password("1234")
					.password("$2a$10$vVgxw0q/lOhcEYKQrr.VbOtBkIU8j8HJNwFpzkhJf5NH3EgGd9QIe")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			userRepository.saveAll(List.of(userHugo, userPaco, userLuis, userAbril));
		};
	}

}
