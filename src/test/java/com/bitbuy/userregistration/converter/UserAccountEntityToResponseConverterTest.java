package com.bitbuy.userregistration.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bitbuy.userregistration.entity.UserAccountEntity;
import com.bitbuy.userregistration.response.UserAccountResponse;

@SpringBootTest
public class UserAccountEntityToResponseConverterTest {

	@Autowired
	private UserAccountEntityToResponseConverter converter;

	@ParameterizedTest
	@MethodSource("entityProvider")
	public void testConvert(UserAccountEntity entity) {
		UserAccountResponse accountResponse = converter.convert(entity);
		assertThat(accountResponse.getUsername()).isEqualTo(entity.getUsername());
		assertThat(accountResponse.getId()).isEqualTo(entity.getId());
	}

	private static Stream<Arguments> entityProvider() {
		return Stream.of(
				Arguments.of(new UserAccountEntity(UUID.randomUUID().toString(), "username1", null, false, null)),
				Arguments.of(new UserAccountEntity(UUID.randomUUID().toString(), "username2", "", false, null)),
				Arguments.of(new UserAccountEntity(UUID.randomUUID().toString(), null, "", false, null)),
				Arguments.of(new UserAccountEntity(null, null, "", false, null)),
				Arguments.of(new UserAccountEntity(null, "username2", "", false, null))
			);
	}

}
