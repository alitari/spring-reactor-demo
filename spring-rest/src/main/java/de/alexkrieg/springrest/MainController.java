package de.alexkrieg.springrest;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@Autowired
	InfoService infoService;

	@RequestMapping("rest/{id}")
	public @ResponseBody ZippedInfo rest(
			@PathVariable("id") long id,
			@RequestParam(value = "service", required = false, defaultValue = "12") String serviceParam)
			throws InterruptedException, ExecutionException {
		Optional<Future<MainInfo>> serviceCall1 = serviceParam.contains("1") ? Optional.of(infoService.serviceCall(id)): Optional.empty();
		Optional<Future<MainInfo>> serviceCall2 = serviceParam.contains("2") ? Optional.of(infoService.serviceCall(id + 10)): Optional.empty() ;

		return new ZippedInfo(
				serviceCall1.isPresent() ? serviceCall1.get().get() : new MainInfo(),
				serviceCall2.isPresent() ? serviceCall2.get().get() : new MainInfo());
	}

}
