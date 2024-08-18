package com.io.securityInfrun.web.admin;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.io.securityInfrun.util.UISMap;
import com.io.securityInfrun.web.admin.service.AdminService;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RoleController {

	//private final RoleService roleService;
	
	@Resource(name="adminService")
	AdminService adminService;

	@GetMapping(value="/admin/roles")
	public String getRoles(Model model) {

		ArrayList<UISMap> roles = adminService.selectRole();
		//List<Role> roles = roleService.getRoles();
		model.addAttribute("roles", roles);

		return "admin/roles";
	}

	@GetMapping(value="/admin/roles/register")
	public String rolesRegister(Model model) {

		//RoleDto role = new RoleDto();
		//model.addAttribute("roles", role);

		return "admin/rolesdetails";
	}

	@PostMapping(value="/admin/roles")
	public String createRole(/*RoleDto roleDto*/) {
		/*ModelMapper modelMapper = new ModelMapper();
		Role role = modelMapper.map(roleDto, Role.class);
		roleService.createRole(role);*/

		return "redirect:/admin/roles";
	}

	@GetMapping(value="/admin/roles/{id}")
	public String getRole(@PathVariable String id, Model model) {
		/*Role role = roleService.getRole(Long.parseLong(id));

		ModelMapper modelMapper = new ModelMapper();
		RoleDto roleDto = modelMapper.map(role, RoleDto.class);
		model.addAttribute("roles", roleDto);*/

		return "admin/rolesdetails";
	}

	@GetMapping(value="/admin/roles/delete/{id}")
	public String removeRoles(@PathVariable String id) {

		//roleService.deleteRole(Long.parseLong(id));

		return "redirect:/admin/roles";
	}
}
