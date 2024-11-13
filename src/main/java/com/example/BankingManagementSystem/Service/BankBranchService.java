package com.example.BankingManagementSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BankingManagementSystem.Dto.BankBranchDTO;
import com.example.BankingManagementSystem.Dto.ResponseWrapper;
import com.example.BankingManagementSystem.Model.BankBranch;
import com.example.BankingManagementSystem.Repository.BankBranchRepo;
import com.example.BankingManagementSystem.Request.BankBranchRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankBranchService {

    @Autowired
    private BankBranchRepo bankBranchRepo;

    // Convert BankBranch entity to DTO
    private BankBranchDTO convertToDto(BankBranch bankBranch) {
        BankBranchDTO dto = new BankBranchDTO();
        dto.setId(bankBranch.getId());
        dto.setIfscCode(bankBranch.getIfscCode());
        dto.setBranchCode(bankBranch.getBranchCode());
        dto.setBranchName(bankBranch.getBranchName());
        dto.setAddress(bankBranch.getAddress());
        dto.setCity(bankBranch.getCity());
        dto.setDistrict(bankBranch.getDistrict());
        dto.setState(bankBranch.getState());
        dto.setZipcode(bankBranch.getZipcode());
        return dto;
    }

    // Add a new bank branch
    public BankBranchDTO addBranch(BankBranchRequest branchRequest) {

        // Convert the request to an entity
        BankBranch bankBranch = new BankBranch();
        bankBranch.setIfscCode(branchRequest.getIfscCode());
        bankBranch.setBranchCode(branchRequest.getBranchCode());
        bankBranch.setBranchName(branchRequest.getBranchName());
        bankBranch.setAddress(branchRequest.getAddress());
        bankBranch.setCity(branchRequest.getCity());
        bankBranch.setDistrict(branchRequest.getDistrict());
        bankBranch.setState(branchRequest.getState());
        bankBranch.setZipcode(branchRequest.getZipcode());

        // Save the branch to the database
        BankBranch savedBankBranch = bankBranchRepo.save(bankBranch);

        // Convert saved branch to DTO and return response
        return convertToDto(savedBankBranch);
    }

    // Get all branches
    public ResponseWrapper<List<BankBranchDTO>> getBranches() {
        List<BankBranch> branches = bankBranchRepo.findAll();

        // Convert the list of bank branches to a list of DTOs
        List<BankBranchDTO> branchDTOs = branches.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new ResponseWrapper<>(branchDTOs, null);
    }

    // Get a specific branch by IFSC code
    public BankBranchDTO getBranchByIfscCode(String ifscCode) {
        Optional<BankBranch> branchOptional = bankBranchRepo.findByIfscCode(ifscCode);

        if (branchOptional.isPresent()) {
            return convertToDto(branchOptional.get());
        }

        return null;  // Branch not found
    }

    // Delete a bank branch by ID
    public boolean deleteBranch(Long id) {
        Optional<BankBranch> branchOptional = bankBranchRepo.findById(id);

        if (branchOptional.isPresent()) {
            bankBranchRepo.delete(branchOptional.get());
            return true;
        }

        return false;  // Branch not found
    }

    // Update a bank branch
    public BankBranchDTO updateBranch(Long id, BankBranchRequest branchRequest) {
        Optional<BankBranch> branchOptional = bankBranchRepo.findById(id);

        if (branchOptional.isPresent()) {
            BankBranch existingBranch = branchOptional.get();
            
            // Update fields
            existingBranch.setIfscCode(branchRequest.getIfscCode());
            existingBranch.setBranchCode(branchRequest.getBranchCode());
            existingBranch.setBranchName(branchRequest.getBranchName());
            existingBranch.setAddress(branchRequest.getAddress());
            existingBranch.setCity(branchRequest.getCity());
            existingBranch.setDistrict(branchRequest.getDistrict());
            existingBranch.setState(branchRequest.getState());
            existingBranch.setZipcode(branchRequest.getZipcode());

            // Save the updated branch
            BankBranch updatedBranch = bankBranchRepo.save(existingBranch);

            return convertToDto(updatedBranch);
        }

        return null;  // Branch not found
    }
}
