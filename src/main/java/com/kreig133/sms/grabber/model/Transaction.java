package com.kreig133.sms.grabber.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author kreig133
 * @version 1.0
 */
public class Transaction implements Request{

    /**номер транзакции*/
    @SerializedName("id")
    public String id;

    /**номер счёта-получателя*/
    @SerializedName("account_income")
    public String account_income;

    /**номер счёта-источника*/
    @SerializedName("account_outcome")
    public String account_outcome;

    /**доход*/
    @SerializedName("income")
    public String income;

    /**расход*/
    @SerializedName("outcome")
    public String outcome;

    /**номер категории напоминания*/
    @SerializedName("category")
    public String category;

    /**плательщик"*/
    @SerializedName("payee")
    public String payee;

    /**комментарий"*/
    @SerializedName("comment")
    public String comment;

    /**валюта, в которой указан доход*/
    @SerializedName("instrument_income")
    public String instrument_income;

    /**валюта, в которой указан расход*/
    @SerializedName("instrument_outcome")
    public String instrument_outcome;

    /**тип счёта-получателя"*/
    @SerializedName("type_income")
    public String type_income;

    /**тип счёта-источника"*/
    @SerializedName("type_outcome")
    public String type_outcome;

    /**направление операции*/
    @SerializedName("direction")
    public String direction;

    /**дата транзакции/*/
	@SerializedName("date")
	public String date;

	/**флаг включенности счёта-получателя в баланс пользователя*/
	@SerializedName("inbalance_income")
	public String inbalance_income;

	/**флаг включенности счёта-источника в баланс пользователя*/
	@SerializedName("inbalance_outcome")
	public String inbalance_outcome;

	/**стоимость покупки пая / валюты*/
	@SerializedName("price")
	public String price;

	/**номер маркера напоминания о возврате долга*/
	@SerializedName("payback_reminder_marker")
	public String payback_reminder_marker;

	/**номер маркера, из которого была внесена транзакция*/
	@SerializedName("marker")
	public String marker;

	/**дата автоматеского импорта транзакции/*/
	@SerializedName("imported")
	public String imported;

    @Override
    public String getURI() {
        return "/instrument/currency/";
    }
}
