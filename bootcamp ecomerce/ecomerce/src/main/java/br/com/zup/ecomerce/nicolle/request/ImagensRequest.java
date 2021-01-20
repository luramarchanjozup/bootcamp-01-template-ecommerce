package br.com.zup.ecomerce.nicolle.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ImagensRequest {
	
	@Size(min = 1)
	@NotNull
	private List<MultipartFile> imagens = new ArrayList<>();

	public ImagensRequest(@Size(min = 1) List<MultipartFile> imagens) {
		this.imagens = imagens;
	}

	public void setImagens(List<MultipartFile> imagens) {
		this.imagens = imagens;
	}

	public List<MultipartFile> getImagens() {
		return imagens;
	}
	
	
	
	
	

}
