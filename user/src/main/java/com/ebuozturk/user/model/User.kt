package com.ebuozturk.user.model;

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*;


@Entity
@Table(name="app_user")
data class User @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    val id:String? = "",
    val firstName: String,
    val middleName: String,
    val lastName: String,
    @Column(unique = true)
    val email: String,
    val password: String,
    val isActive: Boolean = false,
    @OneToMany(mappedBy = "user",cascade = [CascadeType.ALL])
    val addresses:Set<Address>? = HashSet(),
    @OneToMany(mappedBy = "user",cascade = [CascadeType.ALL])
    val cards:Set<Cart>? = HashSet(),

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != null && id != other.id) return false
        if (firstName != other.firstName) return false
        if (middleName != other.middleName) return false
        if (lastName != other.lastName) return false
        if (email != other.email) return false
        if (isActive != other.isActive) return false
        if (addresses != other.addresses) return false
        if (cards != other.cards) return false
        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + firstName.hashCode()
        result = 31 * result + middleName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + isActive.hashCode()
        result = 31 * result + addresses.hashCode()
        result = 31 * result + cards.hashCode()
        return result
    }
}
