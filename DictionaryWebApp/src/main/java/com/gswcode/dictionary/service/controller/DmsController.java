package com.gswcode.dictionary.service.controller;

import com.gswcode.dictionary.service.client.dto.ServiceStatusDto;
import com.gswcode.dictionary.service.client.mapper.DictionaryMapper;
import com.gswcode.dictionary.service.client.mapper.ItemMapper;
import com.gswcode.dictionary.service.model.Dictionary;
import com.gswcode.dictionary.service.model.Item;
import com.gswcode.dictionary.service.service.DictionaryService;
import com.gswcode.dictionary.service.service.ItemService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("dms")
public class DmsController {

    private final DictionaryService dictionaryService;
    private final ItemService itemService;

    public DmsController(DictionaryService dictionaryService, ItemService itemService) {
        this.dictionaryService = dictionaryService;
        this.itemService = itemService;
    }

    @GetMapping
    public String viewHomePage(Model model) {
        model.addAttribute("dictionaryList", dictionaryService.getDictionaries());
        if (!dictionaryService.isServerAvailable()) {
            return "redirect:/dms/server-unavailable";
        }
        return "dictionary_list";
    }

    @GetMapping("/server-unavailable")
    public String viewServerUnavailable(Model model) {
        dictionaryService.getDictionaries();
        model.addAttribute("error", "Server is temporarily  unavailable");
        if (dictionaryService.isServerAvailable()) {
            return "redirect:/dms";
        }
        return "dictionary_list_server_unavailable";
    }

    @GetMapping("/newDictionaryForm")
    public String showNewDictionaryForm(Model model) {
        Dictionary dictionary = new Dictionary();
        model.addAttribute("dictionary", dictionary);
        model.addAttribute("dictionaries", DictionaryMapper.NAMES_MAP.values());
        return "new_dictionary";
    }

    @PostMapping("/saveDictionary")
    public String saveDictionary(@ModelAttribute("dictionary") Dictionary dictionary, Model model, RedirectAttributes redirAttrs) {
        System.out.println("Saving" + dictionary);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
        dictionary.setCreatedAt(LocalDateTime.now().format(formatter));
        dictionary.setStatus("Active");
        String message = dictionaryService.saveDictionary(dictionary);
        redirAttrs.addFlashAttribute("success", message);
        return "redirect:/dms";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Dictionary dictionary = dictionaryService.getDictionaryById(id);
        model.addAttribute("dictionary", dictionary);
        model.addAttribute("dictionaries", 
                DictionaryMapper.NAMES_MAP.values()
                        .stream()
                        .filter(t -> t.getId() != id)
                        .collect(Collectors.toList()));
        return "update_dictionary";
    }

    @GetMapping("/deactivateDictionary/{id}")
    public String deactivateDictionary(@PathVariable(value = "id") long id, Model model, RedirectAttributes redirAttrs) {
        String message = dictionaryService.deactivateDictionary(id);
        redirAttrs.addFlashAttribute("success", message);
        return "redirect:/dms";
    }

    @GetMapping("/activateDictionary/{id}")
    public String activateDictionary(@PathVariable(value = "id") long id, Model model, RedirectAttributes redirAttrs) {
        String message = dictionaryService.activateDictionary(id);
        redirAttrs.addFlashAttribute("success", message);
        return "redirect:/dms";
    }

    @GetMapping("/deleteDictionary/{id}")
    public String deleteDictionary(@PathVariable(value = "id") long id, Model model, RedirectAttributes redirAttrs) {
        String message = dictionaryService.deleteDictionary(id);
        redirAttrs.addFlashAttribute("success", message);
        return "redirect:/dms";
    }

    @GetMapping("/showItems/{dictionaryId}")
    public String showItems(@PathVariable(value = "dictionaryId") long dictionaryId, Model model, HttpSession session, RedirectAttributes redirAttrs) {
        String headerInfo = "Items of dictionary " + DictionaryMapper.NAMES_MAP.get(dictionaryId).getName();
        model.addAttribute("itemList", itemService.getItems(dictionaryId));
        model.addAttribute("headerInfo", headerInfo);
        model.addAttribute("dictionaryId", dictionaryId);
        session.setAttribute("currentDictionary", dictionaryId);
        return "item_list";
    }

    @GetMapping("/newItemForm/{dictionaryId}")
    public String showNewItemForm(@PathVariable(value = "dictionaryId") long dictionaryId, Model model) {
        Item item = new Item();
        item.setDictionaryId(dictionaryId);
        model.addAttribute("item", item);
        model.addAttribute("items",
                ItemMapper.ITEM_NAMES_MAP.values()
                        .stream()
                        .filter(t -> t.getIdDictionary() == dictionaryId)
                        .collect(Collectors.toList()));
        return "new_item";
    }

    @PostMapping("/saveItem")
    public String saveItem(@ModelAttribute("item") Item item, Model model, RedirectAttributes redirAttrs) {
        String message = itemService.saveItem(item);
        redirAttrs.addFlashAttribute("success", message);
        return "redirect:/dms/showItems/" + item.getDictionaryId();
    }

    @PostMapping("/updateItem")
    public String updateItem(@ModelAttribute("item") Item item, Model model, HttpSession session, RedirectAttributes redirAttrs) {
        String message = itemService.saveItem(item);
        redirAttrs.addFlashAttribute("success", message);
        return "redirect:/dms/showItems/" + session.getAttribute("currentDictionary");
    }

    @GetMapping("/updateItemForm/{currDictionaryId}/{id}")
    public String updateItemForm(@PathVariable(value = "currDictionaryId") long dictionaryId, @PathVariable(value = "id") long id, Model model) {
        Item item = itemService.getItemById(id);
        Dictionary currDict = new Dictionary();
        currDict.setId(dictionaryId);
        model.addAttribute("redirectToDictionary", currDict);
        model.addAttribute("item", item);
        model.addAttribute("items",
                ItemMapper.ITEM_NAMES_MAP.values()
                        .stream()
                        .filter(t -> t.getIdDictionary() == item.getDictionaryId())
                        .filter(t -> t.getId() != id)
                        .collect(Collectors.toList()));
        return "update_item";
    }

    @GetMapping("/deactivateItem/{currDictionaryId}/{itemId}")
    public String deactivateItem(@PathVariable(value = "currDictionaryId") long dictionaryId, @PathVariable(value = "itemId") long itemId, Model model, RedirectAttributes redirAttrs) {
        String message = itemService.deactivateItem(itemId);
        redirAttrs.addFlashAttribute("success", message);
        return "redirect:/dms/showItems/" + dictionaryId;
    }

    @GetMapping("/activateItem/{currDictionaryId}/{itemId}")
    public String activateItem(@PathVariable(value = "currDictionaryId") long dictionaryId, @PathVariable(value = "itemId") long itemId, Model model, RedirectAttributes redirAttrs) {
        ServiceStatusDto status = itemService.activateItem(itemId);
        String message = status.getMessage();
        if (status.isSuccess()) {
            redirAttrs.addFlashAttribute("success", message);
        } else {
            redirAttrs.addFlashAttribute("error", message);
        }
        return "redirect:/dms/showItems/" + dictionaryId;
    }

    @GetMapping("/deleteItem/{dictionaryId}/{itemId}")
    public String deleteItem(@PathVariable(value = "dictionaryId") long dictionaryId, @PathVariable(value = "itemId") long itemId, Model model, RedirectAttributes redirAttrs) {
        String message = itemService.deleteItem(itemId);
        redirAttrs.addFlashAttribute("success", message);
        return "redirect:/dms/showItems/" + dictionaryId;
    }

}
