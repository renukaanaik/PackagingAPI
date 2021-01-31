package com.mobiquity.packer.rules;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.ItemContainer;

/**
 * Rules interface
 * This interface depicts chain of responsibility patter
 *
 * @author Renuka Naik
 */
public interface PackageRules {
    void setNextRule(PackageRules rule);

    void applyRule(ItemContainer container) throws APIException;
}
