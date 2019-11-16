package com.enigma.service.serviceImpl;

import com.enigma.entity.OrderList;
import com.enigma.entity.TableEntities;
import com.enigma.entity.Transaction;
import com.enigma.exeption.NotEnoughtMoneyException;
import com.enigma.repositories.TransactionRepositories;
import com.enigma.service.TableService;
import com.enigma.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ImplementTransaction implements TransactionService {
    @Autowired
    TransactionRepositories transactionRepositories;
    @Autowired
    TableService tableService;

    @Override
    public Transaction saveTransaction(OrderList newOrderList) {
        TableEntities table = tableService.getTableById(newOrderList.getIdTable());
        Transaction transactionData =new Transaction();
        transactionData.setOrderList(newOrderList);
        transactionData.setTableEntities(table);
        transactionData.setTotal(newOrderList.getTotalPrice());
        transactionData.setPaymentStatus("UNPAID");
        transactionData.setPaymentDate(new Date());
        transactionData.setPay(0);
        transactionData =transactionRepositories.save(transactionData);
        return transactionData;
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepositories.findAll();
    }

    @Override
    public Transaction getTransactionById(String idTransaction) {
        return transactionRepositories.findById(idTransaction).get();
    }

    @Override
    public Transaction updatePaymentStatus(Transaction transaction) {
        Transaction transactionData= getTransactionById(transaction.getIdTransaction());
        if (transaction.getPay()<transactionData.getTotal()){
            throw new NotEnoughtMoneyException();
        }else {
            transactionData.setPaymentStatus("PAID");
            transactionData.setPaymentDate(new Date());
            transactionData.setPay(transaction.getPay());
            transactionData.setChange(transaction.getTotal());
            updateStatusTable(transaction);
            transactionData=transactionRepositories.save(transactionData);
        }
        return transactionData;
    }

    private void updateStatusTable(Transaction transaction) {
        TableEntities table= tableService.getTableById(transaction.getOrderList().getTable().getIdTable());
        table.setStatus("AVAILABLE");
        tableService.saveTable(table);
    }

    @Override
    public Page<Transaction> getTransactionByPage(Pageable pageable) {
        return transactionRepositories.findAllByOrderByPaymentDateDesc(pageable);
    }

    @Override
    public Transaction getTransactionByTable(String tableId) {
        TableEntities table =tableService.getTableById(tableId);
        return transactionRepositories.getTransactionByTableEntitiesAndPaymentStatus(table,"UNPAID");
    }
}
