package org.akinator;

public interface Parser<O, D> {

    D parse(O object);
    O unparse(D data);

}