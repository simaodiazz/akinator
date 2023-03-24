package org.akinator;

public interface Parser<Object, Data> {

    Data parse(Object object);
    Object unparse(Data data);

}