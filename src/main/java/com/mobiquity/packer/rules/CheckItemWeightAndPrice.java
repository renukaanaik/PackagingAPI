package com.mobiquity.packer.rules;

import com.mobiquity.constants.PackagingConstants;
import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.model.ItemContainer;
import lombok.extern.slf4j.Slf4j;

/**
 * Class represents rule 3  - which checks per item weight and proce limit
 *
 * @author Renuka Naik
 */
@Slf4j
public class CheckItemWeightAndPrice implements PackageRules{
    private PackageRules rule;

    @Override
    public void setNextRule(PackageRules nextRule) {
        this.rule = nextRule;
    }

    @Override
    public void applyRule(ItemContainer container) throws APIException {

        for (Item item : container.getItemList()) {
            if (item.getItemWeight() > PackagingConstants.PER_ITEM_WEIGHT_LIMIT) {

                log.debug("Rule Voilated :" + PackagingConstants.PER_ITEM_WEIGHT_LIMIT_MSG);
                throw new APIException(PackagingConstants.PER_ITEM_WEIGHT_LIMIT_MSG);
            }
            if (item.getItemPrice().compareTo(PackagingConstants.PER_ITEM_PRICE_LIMIT) > 0) {

                log.debug("Rule Voilated :" + PackagingConstants.PER_ITEM_PRICE_LIMIT_MSG);
                throw new APIException(PackagingConstants.PER_ITEM_PRICE_LIMIT_MSG);
            }
        }

        this.rule.applyRule(container);

    }
}
