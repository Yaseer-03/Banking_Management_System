package com.example.BankingManagementSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.example.BankingManagementSystem.Dto.*;
import com.example.BankingManagementSystem.Request.BankBranchRequest;
import com.example.BankingManagementSystem.Service.BankBranchService;
import com.example.BankingManagementSystem.ResponseStatusCode.ResponseService;

@RestController
@RequestMapping("/api/bank-branch")
public class BankBranchController {

    @Autowired
    private BankBranchService bankBranchService;

    @Autowired
    private ResponseService responseService;

    // Create a new bank branch
    @PostMapping("/add-branch")
    public ResponseEntity<ResponseWrapper<BankBranchDTO>> addBranch(@RequestBody BankBranchRequest branchRequest) {
        BankBranchDTO branchDTO = bankBranchService.addBranch(branchRequest);
        if (branchDTO == null) {
            return responseService.createErrorResponse("Failed to create branch");
        }
        return responseService.createCreatedResponse(branchDTO);  // 201 Created
    }

    // Get a bank branch by IFSC code
    @GetMapping("/get-branch/{ifscCode}")
    public ResponseEntity<ResponseWrapper<BankBranchDTO>> getBranchByIfscCode(@PathVariable String ifscCode) {
        BankBranchDTO branchDTO = bankBranchService.getBranchByIfscCode(ifscCode);
        if (branchDTO == null) {
            return responseService.createNotFoundResponse("Branch not found with IFSC Code: " + ifscCode);  // 404 Not Found
        }
        return responseService.createSuccessResponse(branchDTO);  // 200 OK
    }

    // Delete a bank branch by ID
    @DeleteMapping("/delete-branch/{id}")
    public ResponseEntity<ResponseWrapper<Void>> deleteBranch(@PathVariable Long id) {
        boolean isDeleted = bankBranchService.deleteBranch(id);
        if (!isDeleted) {
            return responseService.createNotFoundResponse("Branch not found with ID: " + id);  // 404 Not Found
        }
        return responseService.createNoContentResponse();  // 204 No Content
    }

    // Update a bank branch
    @PutMapping("/update-branch/{id}")
    public ResponseEntity<ResponseWrapper<BankBranchDTO>> updateBranch(@PathVariable Long id, @RequestBody BankBranchRequest branchRequest) {
        BankBranchDTO updatedBranch = bankBranchService.updateBranch(id, branchRequest);
        if (updatedBranch == null) {
            return responseService.createNotFoundResponse("Branch not found with ID: " + id);  // 404 Not Found
        }
        return responseService.createSuccessResponse(updatedBranch);  // 200 OK
    }
}
