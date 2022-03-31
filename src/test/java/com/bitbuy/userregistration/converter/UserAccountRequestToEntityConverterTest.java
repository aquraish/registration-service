package com.bitbuy.userregistration.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bitbuy.userregistration.entity.UserAccountEntity;
import com.bitbuy.userregistration.request.UserAccountRequest;

@ExtendWith({MockitoExtension.class})
@SpringBootTest
class UserAccountRequestToEntityConverterTest {

	@InjectMocks
	private UserAccountRequestToEntityConverter converter;

	@Mock
	private PasswordEncoder passwordEncoder;

	@ParameterizedTest
	@MethodSource("requestProvider")
	public void testConvert(UserAccountRequest request) {
		when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
		UserAccountEntity entity = converter.convert(request);
		assertThat(request.getUsername()).isEqualTo(entity.getUsername());
		assertThat(entity.getPassword()).isEqualTo("encodedPassword");
	}

	@ParameterizedTest
	@MethodSource("invalidRequestProvider")
	public void testInvalidConvert(UserAccountRequest request) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			converter.convert(request);
		});
	}

	private static Stream<Arguments> requestProvider() {
		return Stream.of(
				Arguments.of(new UserAccountRequest("username", "password")),
				Arguments.of(new UserAccountRequest("username1", "password1")));
	}

	private static Stream<Arguments> invalidRequestProvider() {
		return Stream.of(Arguments.of(new UserAccountRequest("username", "")),
				Arguments.of(new UserAccountRequest("", "password")),
				Arguments.of(new UserAccountRequest(null, "password")),
				Arguments.of(new UserAccountRequest("username", null)),
				Arguments.of(new UserAccountRequest(null, null)));
	}

}
