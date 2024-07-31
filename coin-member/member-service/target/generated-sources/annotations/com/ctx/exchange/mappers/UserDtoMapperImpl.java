package com.ctx.exchange.mappers;

import com.ctx.exchange.domain.User;
import com.ctx.exchange.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-31T14:38:06+0400",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 11.0.23 (Oracle Corporation)"
)
@Component
public class UserDtoMapperImpl implements UserDtoMapper {

    @Override
    public UserDto convert2Dto(User source) {
        if ( source == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( source.getId() );
        userDto.setUsername( source.getUsername() );
        userDto.setCountryCode( source.getCountryCode() );
        userDto.setMobile( source.getMobile() );
        userDto.setEmail( source.getEmail() );
        userDto.setRealName( source.getRealName() );

        return userDto;
    }

    @Override
    public User convert2Entity(UserDto source) {
        if ( source == null ) {
            return null;
        }

        User user = new User();

        user.setId( source.getId() );
        user.setUsername( source.getUsername() );
        user.setCountryCode( source.getCountryCode() );
        user.setMobile( source.getMobile() );
        user.setEmail( source.getEmail() );
        user.setRealName( source.getRealName() );

        return user;
    }

    @Override
    public List<UserDto> convert2Dto(List<User> source) {
        if ( source == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( source.size() );
        for ( User user : source ) {
            list.add( convert2Dto( user ) );
        }

        return list;
    }

    @Override
    public List<User> convert2Entity(List<UserDto> source) {
        if ( source == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( source.size() );
        for ( UserDto userDto : source ) {
            list.add( convert2Entity( userDto ) );
        }

        return list;
    }
}
