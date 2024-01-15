package com.article.test0115.article;

import com.article.test0115.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getList(String keyword) {

        return this.articleRepository.findAllByKeyword(keyword);
    }

    public void create(String title, String content, SiteUser siteUser) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setAuthor(siteUser);
        article.setCreateDate(LocalDateTime.now());

        this.articleRepository.save(article);
    }

    public Article getArticle(Integer id) {
        Optional<Article> oa = this.articleRepository.findById(id);
        if (oa.isEmpty()) {
            throw new RuntimeException();
        }

        return oa.get();
    }

    public void modify(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);
        article.setModifyDate(LocalDateTime.now());

        this.articleRepository.save(article);
    }

    public void delete(Integer id) {
        this.articleRepository.deleteById(id);
    }
}
