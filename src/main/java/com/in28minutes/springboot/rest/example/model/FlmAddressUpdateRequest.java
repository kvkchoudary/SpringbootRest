package com.in28minutes.springboot.rest.example.model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FlmAddressUpdateRequest {

	@Schema(example = "2001 KITTY HAWK AVE", name = "address1", required = true)
	@NotBlank(message = "Address1 is required")
	private String address1;

	@Schema(example = "KITTY HAWK AVE", name = "address2", required = false)
	private String address2;

	@Schema(example = "HAWK AVE", name = "address3", required = false)
	private String address3;

	@Schema(example = "PHILADELPHIA", name = "city", required = false)
	private String city;

	@Schema(example = "PA", name = "stateProvince", required = false)
	private String stateProvince;

	@Schema(example = "191121815", name = "postalCode", required = true)
	@NotBlank(message = "Postal Code is required")
	@Size(min=0,max=11,message = "Postal Code cannot be greater than 11 characters")
	private String postalCode;

	@Schema(example = "840", name = "countryCode", required = true)
	@NotBlank(message = "Country Code is required")
	private String countryCode;

	@Schema(example = "0", name = "picsTerminal", required = false)
	@Digits(integer = 6, fraction = 0)
	private int picsTerminal;

	@Schema(example = "786489536965", name = "trackId", required = true)
	@NotBlank(message = "Track Id is required")
	private String trackId;

	@Schema(example = "Vance Smith", name = "contactName", required = false)
	private String contactName;

	@Schema(example = "Vance Smith", name = "firmName", required = false)
	private String firmName;

	@Schema(example = "201709", name = "vintage", required = true)
	@NotBlank(message = "Vintage is required")
	private String vintage;

}
