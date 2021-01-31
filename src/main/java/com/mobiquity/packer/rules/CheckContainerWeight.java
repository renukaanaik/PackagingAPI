package com.mobiquity.packer.rules;

import com.mobiquity.constants.PackagingConstants;
import com.mobiquity.exception.APIException;
import com.mobiquity.model.ItemContainer;
import lombok.extern.slf4j.Slf4j;

/**
 * Class represents rule 1  - which checks the container weight limit
 *
 * @author Renuka Naik
 */
@Slf4j
public class CheckContainerWeight implements PackageRules {

    private PackageRules rule;

    @Override
    public void setNextRule(PackageRules nextRule) {
        this.rule = nextRule;
    }

    @Override
    public void applyRule(ItemContainer container) throws APIException {

        if (container.getWeightLimit() > PackagingConstants.PER_CONTAINER_WEIGHT_LIMIT) {

            log.debug("Rule Voilated :" + PackagingConstants.PER_CONTAINER_WEIGHT_LIMIT_MSG);
            throw new APIException(PackagingConstants.PER_CONTAINER_WEIGHT_LIMIT_MSG);
        } else {
            this.rule.applyRule(container);
        }
    }
}
