package br.com.johnatanbrayan.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.johnatanbrayan.domain.Produto;
import br.com.johnatanbrayan.repository.ProdutoRepository;
import br.com.johnatanbrayan.service.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
    
    @Autowired
    ProdutoRepository produtoRepository;

    public Produto find(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado. Id :" + id + ",tipo: " + Produto.class.getName()));
    }

    public Page<Produto> findPageProduto(Integer page, Integer linePerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
        Page<Produto> pageProdutos = produtoRepository.findAll(pageRequest);
        return pageProdutos;
    }
}