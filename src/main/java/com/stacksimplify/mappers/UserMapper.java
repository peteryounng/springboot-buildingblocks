package com.stacksimplify.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.stacksimplify.dto.UserMsDto;
import com.stacksimplify.entities.User;

@Mapper
public interface UserMapper {
    
	//User To UserMsDto
	@Mapping(source = "email", target="emailadress")
	public abstract UserMsDto userToUserMsDto(User user);
	
	//List<User> to List<UserMsDto>
	public abstract List<UserMsDto> usersToUserDtos(List<User> users);
}
