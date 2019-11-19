package com.google.cloud.storage;

import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;
import com.google.cloud.Identity;
import com.google.cloud.Role;
import com.google.api.services.storage.model.Policy;

class PolicyHelper
{
    static com.google.cloud.Policy convertFromApiPolicy(final Policy policy) {
        final com.google.cloud.Policy.Builder builder = com.google.cloud.Policy.newBuilder();
        for (final Policy.Bindings bindings : policy.getBindings()) {
            final Iterator iterator2 = bindings.getMembers().iterator();
            while (iterator2.hasNext()) {
                builder.addIdentity(Role.of(bindings.getRole()), Identity.valueOf((String)iterator2.next()), new Identity[0]);
            }
        }
        return builder.setEtag(policy.getEtag()).build();
    }
    
    static Policy convertToApiPolicy(final com.google.cloud.Policy policy) {
        final ArrayList<Policy.Bindings> bindings = new ArrayList<Policy.Bindings>(policy.getBindings().size());
        for (final Map.Entry<K, Set> entry : policy.getBindings().entrySet()) {
            final ArrayList members = new ArrayList<String>(entry.getValue().size());
            final Iterator<Identity> iterator2 = entry.getValue().iterator();
            while (iterator2.hasNext()) {
                members.add(iterator2.next().strValue());
            }
            bindings.add(new Policy.Bindings().setMembers((List)members).setRole(((Role)entry.getKey()).getValue()));
        }
        return new Policy().setBindings((List)bindings).setEtag(policy.getEtag());
    }
    
    private PolicyHelper() {
        super();
    }
}
