package com.mobiquity.packer.rules;

import com.mobiquity.constants.PackagingConstants;
import com.mobiquity.exception.APIException;
import com.mobiquity.model.ItemContainer;
import lombok.extern.slf4j.Slf4j;

/**
 * Class represents rule 2  - which checks the total no of items in container
 *
 * @author Renuka Naik
 */
@Slf4j
public class CheckItemQuantity implements PackageRules {
    private PackageRules rule;

    @Override
    public void setNextRule(PackageRules nextRule) {
        this.rule = nextRule;
    }

    @Override
    public void applyRule(ItemContainer container) throws APIException {

        if (container.getItemList().length > PackagingConstants.MAX_ITEMS_ALLOWED) {

            log.debug("Rule Voilated :" + PackagingConstants.MAX_ITEMS_ALLOWED_MSG);
            throw new APIException(PackagingConstants.MAX_ITEMS_ALLOWED_MSG);
        } else {
            this.rule.applyRule(container);
        }
    }
}
