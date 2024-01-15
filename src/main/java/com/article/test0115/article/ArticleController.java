package com.article.test0115.article;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    @GetMapping("/list")
    public String list(Model model){
        List<Article> articleList = this.articleService.getList();
        model.addAttribute("articleList", articleList);
        return "article_list";
    }
    @GetMapping("/create")
    public String create(ArticleForm articleForm){
        return "article_form";
    }
    @PostMapping("/create")
    public String create(@Valid ArticleForm articleForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "article_form";
        }
        this.articleService.create(articleForm.getTitle(),articleForm.getContent());

        return "redirect:/article/list";
    }
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id){
        Article  article = this.articleService.getArticle(id);
        model.addAttribute("article", article);

        return "article_detail";
    }
    @GetMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Integer id,ArticleForm articleForm){
        Article article = this.articleService.getArticle(id);
        model.addAttribute(article);
        return "article_modifyForm";
    }
    @PostMapping("/modify/{id}")
    public String modfiy(Model model, @PathVariable("id") Integer id, @Valid ArticleForm articleForm, BindingResult bindingResult){
        Article article = this.articleService.getArticle(id);
        this.articleService.modify(article,articleForm.getTitle(),articleForm.getContent());
        return  String.format("redirect:/article/detail/%s", id);
    }
}
