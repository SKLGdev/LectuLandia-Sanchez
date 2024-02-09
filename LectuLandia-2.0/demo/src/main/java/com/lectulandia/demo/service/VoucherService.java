package com.lectulandia.demo.service;

import com.lectulandia.demo.entity.*;
import com.lectulandia.demo.repository.ClientRepository;
import com.lectulandia.demo.repository.ProductRepository;
import com.lectulandia.demo.repository.VoucherDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VoucherService {

    @Autowired
    private VoucherDetailRepository voucherDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired(required = true)
    private RestTemplate restTemplate = new RestTemplate();

    //CREACION DE LOS DETALLES DEL COMPROBANTE
    public VoucherDTO getVoucherDetailCreated(VoucherDetail voucherDetail) {
        Boolean clientExist = getIfExistClient(voucherDetail.getClient());

        Boolean productsExist = getIfExistProduct(voucherDetail.getLines());

        Boolean stockExist = getIfExistsStock(voucherDetail.getLines());

        if (clientExist && productsExist && stockExist) {

            VoucherDetail saveVoucher = getBuildVoucherDetail(voucherDetail);

            setUpdateStock(saveVoucher.getLines());

            return getCreatedVoucherDTO(voucherDetailRepository.save(saveVoucher));
        } else {
            return new VoucherDTO();
        }
    }

    //OBTENCION DE LOS COMPROBANTES POR ID
    public Optional<VoucherDetail> getVoucherDetailById(Long id) {
        return voucherDetailRepository.findById(id);
    }

    //OBTENCION DE TODOS LOS COMPROBANTES
    public List<VoucherDetail> getAllVoucherDetailsData() {
        return voucherDetailRepository.findAll();
    }

    public VoucherDetail getVoucherDetailUpdatedById(Long id, VoucherDetail voucherDetail) {
        VoucherDetail updateVoucherDetail = voucherDetailRepository.findById(id).get();

        updateVoucherDetail.setVoucherName(voucherDetail.getVoucherName());
        updateVoucherDetail.setDescription(voucherDetail.getDescription());
        updateVoucherDetail.setQuantity(voucherDetail.getQuantity());
        updateVoucherDetail.setSection(voucherDetail.getSection());
        updateVoucherDetail.setLastUpdateDate(voucherDetail.getLastUpdateDate());
        updateVoucherDetail.setTotalPrice(voucherDetail.getTotalPrice());

        return voucherDetailRepository.save(updateVoucherDetail);
    }

    public String getVoucherDetailDeletedById(Long id) {
        voucherDetailRepository.deleteById(id);
        return "SUCCESSFUL SALE DETAIL DELETED";
    }


    //--------------------
    private Boolean getIfExistClient(ClientData client) {
        Optional<ClientData> opClient = this.clientRepository.findById(client.getId());
        return opClient.isPresent();
    }

    //----------------------------------------------------------------
    private Boolean getIfExistProduct(Set<Line> product) {
        for (Line line : product) {
            Long id = line.getProduct().getId();
            Optional<Product> opProduct = Optional.empty();
            opProduct = productRepository.findById(id);

            if (opProduct.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    //----------------------------------------------------------------
    private Boolean getIfExistsStock(Set<Line> stock) {
        Optional<Product> opStock = Optional.empty();
        for (Line line : stock) {
            Long id = line.getProduct().getId();
            opStock = productRepository.findById(id);

            if (opStock.isEmpty()) {
                return false;
            }
            if (line.getQuantity() < opStock.get().getStock()) {
                return true;
            }
        }

        return opStock.isPresent();
    }

    //----------------------------------------------------------------
    private BigDecimal getTotal(Set<Line> total) {
        BigDecimal number = new BigDecimal(0L);

        for (Line line : total) {
            number = number.add(new BigDecimal(line.getPrice().toString()));
        }

        return number;
    }

    //----------------------------------------------------------------
    private Line getLineCreated(Line line) {
        Line saveLine = new Line();
        Product product = productRepository.findById(line.getProduct().getId()).get();

        saveLine.setQuantity(line.getQuantity());
        saveLine.setDescription(product.getProductDescription());
        saveLine.setPrice(product.getProductPrice());
        saveLine.setProduct(product);

        return saveLine;
    }

    //----------------------------------------------------------------
    private void setUpdateStock(Set<Line> lines) {
        for (Line line : lines) {
            Long quantitySold = Long.valueOf(line.getQuantity());

            Optional<Product> opProduct = Optional.ofNullable(line.getProduct());
            Product DBProduct = productRepository.getReferenceById(opProduct.get().getId());

            Long stock = Long.valueOf(DBProduct.getStock());
            long newStock = stock - quantitySold;

            DBProduct.setStock((int) newStock);
            productRepository.save(DBProduct);
        }
    }

    //----------------------------------------------------------------
    private VoucherDTO getCreatedVoucherDTO(VoucherDetail voucher) {
        VoucherDTO voucherDTO = new VoucherDTO();

        voucherDTO.setVoucherName(voucher.getVoucherName());
        voucherDTO.setDescription(voucher.getDescription());
        voucherDTO.setQuantity(voucher.getQuantity());
        voucherDTO.setSection(voucher.getSection());
        voucherDTO.setCreationDate(voucher.getCreationDate());
        voucherDTO.setLastUpdateDate(voucher.getLastUpdateDate());
        voucherDTO.setTotalPrice(voucher.getTotalPrice());
        voucherDTO.setClient(voucher.getClient());
        voucherDTO.setProduct(voucher.getProduct());
        voucherDTO.setLines(voucher.getLines());

        return voucherDTO;
    }

    private List<VoucherDTO> getVouchersDTOs(List<VoucherDetail> vouchers) {
        List<VoucherDTO> voucherDTOs = new ArrayList<VoucherDTO>();

        for (VoucherDetail voucher : vouchers) {
            voucherDTOs.add(getCreatedVoucherDTO(voucher));
        }

        return voucherDTOs;
    }

    private Set<LineDTO> getCreatedLines(Set<Line> lines) {
        Set<LineDTO> lineDTOs = new HashSet<LineDTO>();

        for (Line line : lines) {
            LineDTO lineDTO = new LineDTO();

            lineDTO.setQuantity(line.getQuantity());
            lineDTO.setDescription(line.getDescription());
            lineDTO.setPrice(line.getPrice());
            lineDTO.setProduct(line.getProduct());

            lineDTOs.add(lineDTO);
        }

        return lineDTOs;
    }

    private VoucherDetail getBuildVoucherDetail(VoucherDetail voucher) {
        VoucherDetail saveVoucherDetail = new VoucherDetail();

        saveVoucherDetail.setClient(clientRepository.findById(voucher.getClient().getId()).get());

        WorldClockAPI worldClockAPI = restTemplate.getForObject("http://worldclockapi.com/api/json/utc/now", WorldClockAPI.class);

        assert worldClockAPI != null;
        String currentDateTime = worldClockAPI.getCurrentDateTime();

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'MM:ss'Z'").parse(currentDateTime);
            saveVoucherDetail.setLastUpdateDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
            saveVoucherDetail.setLastUpdateDate(new Date());
        }

        saveVoucherDetail.setLines(new HashSet<>());
        for (Line line : voucher.getLines()) {
            saveVoucherDetail.addLinea(getLineCreated(line));
        }

        saveVoucherDetail.setTotalPrice(getTotal(saveVoucherDetail.getLines()));
        saveVoucherDetail.setQuantity(voucher.getLines().size());

        return saveVoucherDetail;
    }
}

