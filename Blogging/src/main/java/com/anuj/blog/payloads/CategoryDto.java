package com.anuj.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer CategoryId;

	@NotBlank
	@Size(min = 4)
	private String CategoryTitle;

	@NotBlank
	@Size(min = 10)
	private String CategoryDescription;
}
