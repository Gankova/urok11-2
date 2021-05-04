package com.company;

import java.util.Scanner;

public class ATM {
    private String cardId;

    private Bank bank;
    public ATM(Bank bank){
        this.bank = bank;
    }

    public void insertCard (Card card){
        // назначение метода запомнить ID карточку
        this.cardId = card.getId();
    }
    public void extractCard() {
        this.cardId ="";

    }
    public void showEnterPinCodeMenu() throws UnsupportedCardException, BlocCardException  {
        Scanner  sc = new Scanner(System.in);
        while (true){
            System.out.println("Введите пин-код");
            int pinCode =sc.nextInt();
            try {
                bank.signIn(this.cardId, pinCode);
            } catch (IncorrectPinException e) {
                System.out.println(e.getMessage());// вернет тело метода IncorrectPinExceotion
                System.out.print("Осталось попыток: ");
                System.out.println(e.getTrieseCount());
                continue;
            }
            break;
        }

    }

    public void withDraw() {
        Scanner sc = new Scanner(System.in);
        int money = 0;
        while (money != -1) {
            System.out.println("Введите сумму для снятия");
            System.out.println("Для завершения введите -1");
            money = sc.nextInt();
            try {
                this.bank.whithDrawMoney(this.cardId, money);
            } catch (NotManyException e) {
                System.out.println(e.getMessage());
            }
            System.out.print("Остаток денег:  ");
            System.out.println(this.bank.getMoney(this.cardId));
        }
    }
}
