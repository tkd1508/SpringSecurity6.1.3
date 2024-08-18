package com.io.securityInfrun.web.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.io.securityInfrun.util.UISMap;
import com.io.securityInfrun.web.admin.service.AdminService;
import com.io.securityInfrun.web.user.service.UserService;

import jakarta.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserManagementController {
	//private final UserManagementService userManagementService;
	//private final RoleService roleService;
	
	@Resource(name="userService")
	UserService userService;
	
	@Resource(name="adminService")
	AdminService adminService;
	
	@GetMapping(value="/admin/users")
	public String getUsers(Model model) {

		ArrayList<UISMap> users = userService.userInfoSelect();
		//List<Account> users = userManagementService.getUsers();
		model.addAttribute("users", users);

		return "admin/users";
	}

	@PostMapping(value="/admin/users")
	public String modifyUser(/*AccountDto accountDto*/) {

		//userManagementService.modifyUser(accountDto);

		return "redirect:/admin/users";
	}

	@GetMapping(value = "/admin/users/{id}")
	public String getUser(@PathVariable(value = "id") Long id, Model model) {
		
		
		UISMap user = userService.findUserInfo2(Integer.parseInt(id.toString()));
		ArrayList<UISMap> roleList = adminService.selectRole();
		//List<Role> roleList = roleService.getRolesWithoutExpression();

		model.addAttribute("user", user);
		model.addAttribute("roleList", roleList);

		return "admin/userdetails";
	}

	@GetMapping(value = "/admin/users/delete/{id}")
	public String removeUser(@PathVariable(value = "id") Long id) {

		//userManagementService.deleteUser(id);

		return "redirect:admin/users";
	}
}
