package com.lectulandia.demo.controller;

import com.lectulandia.demo.entity.VoucherDTO;
import com.lectulandia.demo.entity.VoucherDetail;
import com.lectulandia.demo.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/vouchers")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @RequestMapping(value = "/test")
    public String index() {
        return "CONNECTED";
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getSaveVoucher(@RequestBody VoucherDetail voucher) {
        VoucherDTO saveVoucher = voucherService.getVoucherDetailCreated(voucher);

        try {
            return ResponseEntity.created(URI.create("")).body(saveVoucher);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().build();

            return ResponseEntity.badRequest().build();

        }
    }

    @GetMapping(value = "/voucher/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getVoucher(@PathVariable(name = "id") Long id) {
        Optional<VoucherDetail> voucher = voucherService.getVoucherDetailById(id);

        try {
            return ResponseEntity.ok(voucher.get());

        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().build();

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/all_vouchers", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllVouchers() {
        List<VoucherDetail> voucher = voucherService.getAllVoucherDetailsData();

        try {
            return ResponseEntity.ok(voucher);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().build();

            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/modified/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public VoucherDetail updateVoucher(@PathVariable Long id, @PathVariable VoucherDetail voucher) {
        try {
            return voucherService.getVoucherDetailUpdatedById(id, voucher);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().build();

            return null;
        }
    }

    @DeleteMapping(value = "/low/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteVoucher(@PathVariable Long id) {
        try {
            return voucherService.getVoucherDetailDeletedById(id);

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

}
