package com.automsg.services;

import com.automsg.domains.*;
import com.automsg.domains.enums.StatusOrdem;
import com.automsg.domains.enums.TipoServico;
import com.automsg.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    @Autowired private MecanicaRepositories mecanicaRepo;
    @Autowired private ClienteRepositories clienteRepo;
    @Autowired private OrdemServicoRepositories ordemRepo;
    @Autowired private ServicoRepositories servicoRepo;
    @Autowired private MensagemRepositories mensagemRepo;


    public void instanciaBaseDeDados() {


        Mecanica m = new Mecanica();
        m.setNome("Mecânica do Carlos");
        m.setEmail("carlos@mecanica.com");
        m.setSenha("123456");
        m.setTelefone("11999999999");
        m.setEndereco("Rua das Oficinas, 123");
        mecanicaRepo.save(m);


        Cliente c1 = new Cliente();
        c1.setNome("João Silva");
        c1.setTelefone("11988887777");
        c1.setVeiculo("Gol 2018");
        c1.setPlaca("ABC-1234");
        c1.setMecanica(m);
        clienteRepo.save(c1);

        Cliente c2 = new Cliente();
        c2.setNome("Maria Souza");
        c2.setTelefone("11977776666");
        c2.setVeiculo("HB20 2020");
        c2.setPlaca("XYZ-9876");
        c2.setObservacoes("Prefere ligar antes de vir buscar");
        c2.setMecanica(m);
        clienteRepo.save(c2);

        Cliente c3 = new Cliente();
        c3.setNome("Pedro Alves");
        c3.setTelefone("11966665555");
        c3.setVeiculo("Civic 2022");
        c3.setPlaca("DEF-5678");
        c3.setMecanica(m);
        clienteRepo.save(c3);


        OrdemServico o1 = new OrdemServico();
        o1.setServico("Troca de óleo");
        o1.setDescricao("Cliente relata barulho no motor");
        o1.setStatus(StatusOrdem.AGUARDANDO);
        o1.setCliente(c1);
        o1.setMecanica(m);
        ordemRepo.save(o1);

        OrdemServico o2 = new OrdemServico();
        o2.setServico("Alinhamento e balanceamento");
        o2.setDescricao("Carro puxando para o lado direito");
        o2.setStatus(StatusOrdem.EM_ANDAMENTO);
        o2.setCliente(c2);
        o2.setMecanica(m);
        ordemRepo.save(o2);

        OrdemServico o3 = new OrdemServico();
        o3.setServico("Troca de freios");
        o3.setDescricao("Pastilhas desgastadas");
        o3.setStatus(StatusOrdem.PRONTO);
        o3.setCliente(c3);
        o3.setMecanica(m);
        ordemRepo.save(o3);


        Servico s1 = new Servico();
        s1.setNome("Troca de óleo");
        s1.setDescricao("Troca de óleo do motor");
        s1.setPreco(80.0);
        s1.setTipo(TipoServico.TROCA_OLEO);
        s1.setMecanica(m);
        servicoRepo.save(s1);

        Servico s2 = new Servico();
        s2.setNome("Alinhamento e balanceamento");
        s2.setDescricao("Alinhamento e balanceamento das rodas");
        s2.setPreco(120.0);
        s2.setTipo(TipoServico.ALINHAMENTO);
        s2.setMecanica(m);
        servicoRepo.save(s2);

        Servico s3 = new Servico();
        s3.setNome("Troca de freios");
        s3.setDescricao("Troca das pastilhas de freio");
        s3.setPreco(200.0);
        s3.setTipo(TipoServico.FREIOS);
        s3.setMecanica(m);
        servicoRepo.save(s3);


        Mensagem msg1 = new Mensagem();
        msg1.setTexto("Olá João! Sua Troca de óleo está em andamento");
        msg1.setTelefoneDestino("11988887777");
        msg1.setEnviado(true);
        msg1.setOrdemServico(o1);
        msg1.setMecanica(m);
        mensagemRepo.save(msg1);

        Mensagem msg2 = new Mensagem();
        msg2.setTexto("Olá Maria! Seu Alinhamento está PRONTO!");
        msg2.setTelefoneDestino("11977776666");
        msg2.setEnviado(true);
        msg2.setOrdemServico(o2);
        msg2.setMecanica(m);
        mensagemRepo.save(msg2);
    }
}