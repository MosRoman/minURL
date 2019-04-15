package com.gmail.morovo1988.minURL.controller;

import com.gmail.morovo1988.minURL.model.MiniURL;
import com.gmail.morovo1988.minURL.model.Statistic;
import com.gmail.morovo1988.minURL.repository.MiniURLReposetory;
import com.gmail.morovo1988.minURL.services.MinimizationURLService;
import com.gmail.morovo1988.minURL.services.StatisticService;
import com.gmail.morovo1988.minURL.wrappers.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;


@Controller
public class MainController {


    private final MinimizationURLService minimizationURLService;

    private final StatisticService statisticService;

    @Autowired
    private MiniURLReposetory miniURLReposetory;

    public MainController(MinimizationURLService minimizationURL, StatisticService statisticService) {
        this.minimizationURLService = minimizationURL;
        this.statisticService = statisticService;
    }

    @GetMapping(value = "/")
    public String index(Model model, final Pageable pageable) {

        final Page<MiniURL> miniURLS = this.minimizationURLService.findAll(pageable);

        model.addAttribute("miniURLS", miniURLS.getContent());

        final PageWrapper<MiniURL> page = new PageWrapper<>(miniURLS, "/");


        model.addAttribute("adress", new MiniURL());
        model.addAttribute("page", page);
        return "index";
    }

    @GetMapping("/add_mini_adress")
    public String formCreateMiniAdress(Model model, HttpServletRequest request) {
        model.addAttribute("adress", new MiniURL());
        return "adress";
    }

    @PostMapping("/add_mini_adress")
    public String addMiniAdress(@ModelAttribute("adress") final @Valid MiniURL req,
                                final BindingResult bindingResult,
                                final Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("adress", req);
            return "adress";
        }

        final MiniURL miniURL = this.minimizationURLService.create(req);

        return "redirect:/";
    }

    @PostMapping("/{id}/delete")
    public String processDeleteMiniAdress(@PathVariable("id") final Long id) {
        MiniURL miniURL = this.minimizationURLService.findById(id);
        this.minimizationURLService.delete(miniURL);
        return "redirect:/";
    }

    @GetMapping("{id}/update")
    public String updateMiniAdress(@PathVariable("id") final Long id, Model model) {
        MiniURL miniURL = this.minimizationURLService.findById(id);
        model.addAttribute("adress", miniURL);
        return "adress";
    }

    @PostMapping("*/update")
    public String processUpdateMiniAdress(
            @ModelAttribute("adress") final @Valid MiniURL miniURL,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("adress", miniURL);
            return "adress";
        }
        this.minimizationURLService.updateURL(miniURL);

        return "redirect:/";
    }

    @GetMapping("{id}")
    public String reload(@PathVariable("id") final Long id, HttpServletRequest request, HttpServletResponse response) {

        MiniURL miniURL = this.minimizationURLService.findById(id);
        if (miniURL.isActive() == true && miniURL.getDayDestroy().isAfter(LocalDate.now())) {
            String ip = request.getRemoteAddr();
            String referer = request.getHeader("referer");
            String clientBrowser = getClientBrowser(request);
            LocalDate localDate = LocalDate.now();
            LocalTime localTime = LocalTime.now();
            Statistic statistic = new Statistic(localDate, localTime, referer, ip, clientBrowser, miniURL);

            this.statisticService.create(statistic);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return "redirect:" + miniURL.getLongAdress();
    }

    @GetMapping("{id}/statistic")
    private String showStatisticMiniURL(@PathVariable("id") final Long id, Model model) {
        model.addAttribute("miniURL", this.minimizationURLService.findById(id));

        model.addAttribute("count", this.statisticService.findAllStatisticByMiniURLId(id).size());
        model.addAttribute("browsers", this.statisticService.findAllStatisticByMiniURLIdAndBrowser(this.minimizationURLService.findById(id)));
        model.addAttribute("referers", this.statisticService.findAllStatisticByMiniURLdAndReferer(this.minimizationURLService.findById(id)));
        model.addAttribute("dates", this.statisticService.findAllStatisticByMiniURLIdAndDate(this.minimizationURLService.findById(id)));

        return "statistic";
    }

    @PostMapping("{id}/active")
    private String setActiveMiniURL(@PathVariable("id") final Long id, Model model) {
        MiniURL miniURL = this.minimizationURLService.findById(id);
        if (miniURL.isActive() == false) {
            miniURL.setActive(true);
        } else {
            miniURL.setActive(false);
        }
        this.minimizationURLService.updateURL(miniURL);

        return "redirect:/";
    }

    public String getClientBrowser(HttpServletRequest request) {
        final String browserDetails = request.getHeader("User-Agent");
        final String user = browserDetails.toLowerCase();

        String browser = "";

        //===============Browser===========================
        if (user.contains("msie")) {
            String substring = browserDetails.substring(browserDetails.indexOf("MSIE")).split(";")[0];
            browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version")) {
            browser = (browserDetails.substring(browserDetails.indexOf("Safari")).split(" ")[0]).split(
                    "/")[0] + "-" + (browserDetails.substring(
                    browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if (user.contains("opr") || user.contains("opera")) {
            if (user.contains("opera"))
                browser = (browserDetails.substring(browserDetails.indexOf("Opera")).split(" ")[0]).split(
                        "/")[0] + "-" + (browserDetails.substring(
                        browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
            else if (user.contains("opr"))
                browser = ((browserDetails.substring(browserDetails.indexOf("OPR")).split(" ")[0]).replace("/",
                        "-")).replace(
                        "OPR", "Opera");
        } else if (user.contains("chrome")) {
            browser = (browserDetails.substring(browserDetails.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf(
                "mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf(
                "mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
            //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
            browser = "Netscape-?";

        } else if (user.contains("firefox")) {
            browser = (browserDetails.substring(browserDetails.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("rv")) {
            browser = "IE";
        } else {
            browser = "UnKnown, More-Info: " + browserDetails;
        }

        return browser;
    }


}
