/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package asw1022.repositories;

import asw1022.db.CardDB;
import asw1022.kb.AppKB;
import asw1022.model.dixit.Card;
import javax.xml.bind.JAXBException;

/**
 * JAXB-based implementation of a Card repository.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public class CardRepositoryJAXB extends RepositoryJAXB<Card> {

    public CardRepositoryJAXB(String xmlDB) throws JAXBException{
        super(xmlDB, AppKB.XML_SCHEMA_DIR+"db/card_db.xsd", CardDB.class, Card.class);
    }
    
}
