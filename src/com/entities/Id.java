
package com.entities;

import java.io.Serializable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Id implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("$oid")
    @Expose
    private String $oid;

    /**
     * 
     * @return
     *     The $oid
     */
    public String get$oid() {
        return $oid;
    }

    /**
     * 
     * @param $oid
     *     The $oid
     */
    public void set$oid(String $oid) {
        this.$oid = $oid;
    }

}
