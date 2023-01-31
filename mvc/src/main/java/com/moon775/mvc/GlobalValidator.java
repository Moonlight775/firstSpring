package com.moon775.mvc;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class GlobalValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
//		return User.class.equals(clazz);	// 검증하려는 객체가 User타입인지 확인
		return User.class.isAssignableFrom(clazz);	// clazz가 User 또는 그 자손인지 확인
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("GlobalValidator.validate() is called");
		
//		if (!target instanceof User) return;	// 형변환 전 미리 확인해야 하지만 supports 메서드로 확인 후 validate를 호출하기 때문에 필요 X
		User user = (User)target;
		
		String id = user.getId();
		
//		if (id==null || "".equals(id.trim())) {
//			errors.rejectValue("id", "required");
//		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");
		
		if (id==null || id.length()<5 || id.length()>12) {
			errors.rejectValue("id", "invalidLength", new String[] {"5","12"}, null);
		}
		
	}

}
