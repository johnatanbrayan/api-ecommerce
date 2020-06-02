package br.com.johnatanbrayan.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.johnatanbrayan.domain.Produto;
import br.com.johnatanbrayan.service.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class produtoResource {

    @Autowired
    ProdutoService produtoService;
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Produto> get(@PathVariable Long id) {
        Produto produto = produtoService.find(id);
        return ResponseEntity.ok().body(produto);
    }

    @RequestMapping(value="/page", method=RequestMethod.GET)
    public ResponseEntity<Page<Produto>> findPageProduto(@RequestParam(value="page", defaultValue="0") Integer page, 
    @RequestParam(value="linePerPage", defaultValue = "24") Integer linePerPage, 
    @RequestParam(value="direction",defaultValue = "ASC")String direction, @RequestParam(value="orderBy", defaultValue="nome") String orderBy) {
        Page<Produto> pageProdutos = produtoService.findPageProduto(page, linePerPage, direction, orderBy);
        return ResponseEntity.ok().body(pageProdutos);
    }
}