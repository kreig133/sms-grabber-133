package com.kreig133.sms.grabber.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author kreig133
 * @version 1.0
 */
public class Transaction implements Request{

    /**номер транзакции*/
    @SerializedName("id")
    public Integer id;

    /**номер счёта-получателя*/
    @SerializedName("account_income")
    public Integer account_income;

    /**номер счёта-источника*/
    @SerializedName("account_outcome")
    public Integer account_outcome;

    /**доход*/
    @SerializedName("income")
    public Double income;

    /**расход*/
    @SerializedName("outcome")
    public Double outcome;

    /**номер категории напоминания*/
    @SerializedName("category")
    public Integer category;

    /**плательщик"*/
    @SerializedName("payee")
    public String payee;

    /**комментарий"*/
    @SerializedName("comment")
    public String comment;

    /**валюта, в которой указан доход*/
    @SerializedName("instrument_income")
    public Integer instrument_income;

    /**валюта, в которой указан расход*/
    @SerializedName("instrument_outcome")
    public Integer instrument_outcome;

    /**тип счёта-получателя"*/
    @SerializedName("type_income")
    public String type_income;

    /**тип счёта-источника"*/
    @SerializedName("type_outcome")
    public String type_outcome;

    /**направление операции*/
    @SerializedName("direction")
    public Integer direction;

    /**дата транзакции/*/
	@SerializedName("date")
	public Date date;

	/**флаг включенности счёта-получателя в баланс пользователя*/
	@SerializedName("inbalance_income")
	public Boolean inbalance_income;

	/**флаг включенности счёта-источника в баланс пользователя*/
	@SerializedName("inbalance_outcome")
	public Boolean inbalance_outcome;

	/**стоимость покупки пая / валюты*/
	@SerializedName("price")
	public Double price;

	/**номер маркера напоминания о возврате долга*/
	@SerializedName("payback_reminder_marker")
	public Integer payback_reminder_marker;

	/**номер маркера, из которого была внесена транзакция*/
	@SerializedName("marker")
	public Integer marker;

	/**дата автоматеского импорта транзакции/*/
	@SerializedName("imported")
	public Date imported;

    @SerializedName("tag_group")
    public int tag_group;

    @Override
    public String getURI() {
        return "/transaction/";
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append( "Transaction" );
        sb.append( "{id=" ).append( id );
        sb.append( ", account_income=" ).append( account_income );
        sb.append( ", account_outcome=" ).append( account_outcome );
        sb.append( ", income=" ).append( income );
        sb.append( ", outcome=" ).append( outcome );
        sb.append( ", category=" ).append( category );
        sb.append( ", payee='" ).append( payee ).append( '\'' );
        sb.append( ", comment='" ).append( comment ).append( '\'' );
        sb.append( ", instrument_income=" ).append( instrument_income );
        sb.append( ", instrument_outcome=" ).append( instrument_outcome );
        sb.append( ", type_income='" ).append( type_income ).append( '\'' );
        sb.append( ", type_outcome='" ).append( type_outcome ).append( '\'' );
        sb.append( ", direction=" ).append( direction );
        sb.append( ", date=" ).append( date );
        sb.append( ", inbalance_income=" ).append( inbalance_income );
        sb.append( ", inbalance_outcome=" ).append( inbalance_outcome );
        sb.append( ", price=" ).append( price );
        sb.append( ", payback_reminder_marker=" ).append( payback_reminder_marker );
        sb.append( ", marker=" ).append( marker );
        sb.append( ", imported=" ).append( imported );
        sb.append( '}' );
        return sb.toString();
    }
}
