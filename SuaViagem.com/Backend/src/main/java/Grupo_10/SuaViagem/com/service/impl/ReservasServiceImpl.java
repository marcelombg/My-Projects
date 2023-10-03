package Grupo_10.SuaViagem.com.service.impl;

import Grupo_10.SuaViagem.com.exception.NotFoundException;
import Grupo_10.SuaViagem.com.model.entity.DTO.ProdutosDTO;
import Grupo_10.SuaViagem.com.model.entity.DTO.ReservasDTO;
import Grupo_10.SuaViagem.com.model.entity.ProdutosEntity;
import Grupo_10.SuaViagem.com.model.entity.ReservasEntity;
import Grupo_10.SuaViagem.com.model.entity.UserEntity;
import Grupo_10.SuaViagem.com.repository.IProdutosRepository;
import Grupo_10.SuaViagem.com.repository.IReservasRepository;
import Grupo_10.SuaViagem.com.repository.IUserRepository;
import Grupo_10.SuaViagem.com.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReservasServiceImpl implements IService<ReservasDTO> {

    @Autowired
    private IReservasRepository iReservasRepository;

    @Autowired
    private IProdutosRepository iProdutosRepository;

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public ReservasDTO register(ReservasDTO reservasDTO) throws NotFoundException {
        if (reservasDTO.getProdutosEntity() == null) {
            throw new NotFoundException("O id do produto não pode ser nulo.");
        }

        // Recupera o objeto de produto correspondente ao ID do produto fornecido
        ProdutosEntity produtosEntity = iProdutosRepository.findById(reservasDTO.getProdutosEntity().getId_produtos())
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));

        // Recupera o objeto de usuário correspondente ao ID do usuário fornecido
        UserEntity userEntity = iUserRepository.findById(reservasDTO.getIdUser())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        Date dataInicial = reservasDTO.getDataInicial();
        Date dataFinal = reservasDTO.getDataFinal();

        // Verifica se já existe uma reserva para o produto no intervalo de datas fornecido
        boolean hasConflict = produtosEntity.getReservasEntity()
                .stream()
                .anyMatch(r -> {
                    Date rDataInicial = r.getDataInicial();
                    Date rDataFinal = r.getDataFinal();
                    return (dataInicial.after(rDataInicial) && dataInicial.before(rDataFinal))
                            || (dataFinal.after(rDataInicial) && dataFinal.before(rDataFinal))
                            || dataInicial.equals(rDataInicial)
                            || dataFinal.equals(rDataFinal)
                            || (dataInicial.equals(dataFinal) && dataFinal.equals(dataInicial));
                });

        if (hasConflict) {
            throw new NotFoundException("Já existe uma reserva para este produto no intervalo de datas fornecido.");
        }

        // Adiciona a nova reserva ao array de reservas do produto
        ReservasEntity reservasEntity = mapperDTOToEntity(reservasDTO);
        produtosEntity.getReservasEntity().add(reservasEntity);

        // Salva o objeto de produto atualizado no banco de dados
        iProdutosRepository.save(produtosEntity);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserEmail = authentication.getName();

        // Configurações do servidor SMTP
        String username = "noreplysuaviagem@gmail.com";
        String password = "qglbxhdkymgzwjed";

        // Configurações do e-mail
        String from = "noreplysuaviagem@gmail.com";
        String to = loggedUserEmail;
        String subject = "SuaViagem.com";
        String message = "Uma nova reserva foi feita.";

        // Configura o servidor SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");


        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        StringBuilder htmlBodyBuilder = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC")); // Define o fuso horário como UTC
        Calendar cal = Calendar.getInstance();
        cal.setTime(reservasDTO.getDataInicial());
        cal.add(Calendar.DATE, -1);
        String dataInicialMenosUmDia = sdf.format(cal.getTime());

        cal.setTime(reservasDTO.getDataFinal());
        cal.add(Calendar.DATE, -1);
        String dataFinalMenosUmDia = sdf.format(cal.getTime());

        htmlBodyBuilder.append("<html>");
        htmlBodyBuilder.append("<head>");
        htmlBodyBuilder.append("<style>");
        htmlBodyBuilder.append("</style>");
        htmlBodyBuilder.append("</head>");
        htmlBodyBuilder.append("<body>");
        htmlBodyBuilder.append("<h1>Nova reserva</h1>");
        htmlBodyBuilder.append("<p>Olá,</p>");
        htmlBodyBuilder.append("<p>Uma nova reserva foi feita para o seguinte produto:</p>");
        htmlBodyBuilder.append("<ul>");
        htmlBodyBuilder.append("<li><strong>Nome do produto:</strong> " + produtosEntity.getNome() + "</li>");
        htmlBodyBuilder.append("<li><strong>Data inicial:</strong> " + dataInicialMenosUmDia + "</li>");
        htmlBodyBuilder.append("<li><strong>Data final:</strong> " + dataFinalMenosUmDia  + "</li>");
        htmlBodyBuilder.append("</ul>");
        htmlBodyBuilder.append("<p>Atenciosamente,<br/>Equipe SuaViagem.com</p>");
        htmlBodyBuilder.append("</body>");
        htmlBodyBuilder.append("</html>");

        String htmlBody = htmlBodyBuilder.toString();

        try {
            // Cria a mensagem de e-mail
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);

            // Cria um objeto Multipart
            Multipart multipart = new MimeMultipart();

            // Adiciona o corpo em HTML à mensagem
            BodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlBody, "text/html; charset=utf-8");
            multipart.addBodyPart(htmlPart);

            // Define o Multipart como conteúdo da mensagem
            mimeMessage.setContent(multipart);

            // Envia a mensagem
            Transport.send(mimeMessage);

            System.out.println("Email de reserva enviado para " + loggedUserEmail);

        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

        return mapperEntityToDTO(reservasEntity);

    }

    @Override
    public List<ReservasDTO> findAll() {
        List<ReservasEntity> reservasEntities = iReservasRepository.findAll();
        List<ReservasDTO> reservasDTOS = new ArrayList<>();

        for (ReservasEntity reservasEntity : reservasEntities){
            ReservasDTO reservasDTO = mapperEntityToDTO(reservasEntity);
            reservasDTOS.add(reservasDTO);
        }
        return reservasDTOS;
    }

    @Override
    public String delete(int id) {
        iReservasRepository.deleteById(id);
        return "Reserva removida!";
    }

    @Override
    public ReservasDTO edit(ReservasDTO reservasDTO, int id) {
        ReservasEntity reservasEntity = mapperDTOToEntity(reservasDTO);

        if(iReservasRepository.findById(id).isPresent()) {
            reservasEntity.setId_reservas(id);
            iReservasRepository.save(reservasEntity);
            return reservasDTO;
        } else {
            iReservasRepository.save(reservasEntity);
            return reservasDTO;
        }
    }

    @Override
    public ReservasDTO findById(int id) throws NotFoundException {
        ReservasEntity reservasEntity = iReservasRepository.findById(id).orElseThrow(()-> new org.webjars.NotFoundException("Reserva não encontrada com o id: " + id));
        ReservasDTO reservasDTO = mapperEntityToDTO(reservasEntity);
        return reservasDTO;
    }

    public List<ReservasDTO> findByIdUser(int idUser) throws NotFoundException {

        List<ReservasEntity> reservasEntityList = iReservasRepository.findByIdUser(idUser);
        List<ReservasDTO> reservasDTOS = new ArrayList<>();

        if (reservasEntityList.isEmpty()) {
            throw new NotFoundException("Nenhuma reserva encontrada para o ID especificado");
        } else {
            for (ReservasEntity reservasEntity : reservasEntityList){
                ReservasDTO reservasDTO = mapperEntityToDTO(reservasEntity);
                reservasDTOS.add(reservasDTO);
            }
            return reservasDTOS;
        }

    }

    private ReservasEntity mapperDTOToEntity(ReservasDTO reservasDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        ReservasEntity reservasEntity = objectMapper.convertValue(reservasDTO, ReservasEntity.class);
        return reservasEntity;
    }

    private ReservasDTO mapperEntityToDTO(ReservasEntity reservasEntity) {
        ObjectMapper objectMapper = new ObjectMapper();
        ReservasDTO reservasDTO = objectMapper.convertValue(reservasEntity, ReservasDTO.class);
        return reservasDTO;
    }
}
