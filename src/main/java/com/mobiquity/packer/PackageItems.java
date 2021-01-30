package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.ItemContainer;

public interface PackageItems {

    void filterItemsToPack(ItemContainer container) throws APIException;

    void validateInputs(ItemContainer container) throws APIException;

}
