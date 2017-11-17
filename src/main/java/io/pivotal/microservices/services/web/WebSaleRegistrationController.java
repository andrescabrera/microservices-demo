package io.pivotal.microservices.services.web;

import io.pivotal.microservices.exceptions.SaleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

/**
 * Client controller, do a Sale Registration from the microservice via
 * {@link WebSalesRegistrationService}.
 * 
 * @author Andres Cabrera
 */
@Controller
public class WebSaleRegistrationController {

	@Autowired
	protected WebSalesRegistrationService saleRegistrationService;

	protected Logger logger = Logger.getLogger(WebSaleRegistrationController.class
			.getName());

	public WebSaleRegistrationController(WebSalesRegistrationService saleRegistrationService) {
		this.saleRegistrationService = saleRegistrationService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("saleNumber", "searchText");
	}

	@RequestMapping("/sales")
	public String goHome() {
		return "index";
	}

	@RequestMapping("/sales/{saleNumber}")
	public String byNumber(Model model,
			@PathVariable("saleNumber") String saleNumber) throws SaleNotFoundException {

		logger.info("web-service byNumber() invoked: " + saleNumber);

		SaleDTO saleDTO = saleRegistrationService.findByNumber(saleNumber);
		logger.info("web-service byNumber() found: " + saleDTO);
		model.addAttribute("sale", saleDTO);
		return "sale";
	}

    @RequestMapping(value = "/sales/doregister")
    public String doRegister(Model model, SaleDTO sale) {
        logger.info("web-service search() invoked: " + sale);

        SaleDTO saleResult = saleRegistrationService.register(sale);
        logger.info("web-service register(): " + sale);
        model.addAttribute("sale", saleResult);
        return "sale";
    }

    @RequestMapping(value = "/sales/register", method = RequestMethod.GET)
    public String saleRegistrationForm(Model model) {
        model.addAttribute("sale", new SaleDTO());
        return "salesRegistration";
    }
}
