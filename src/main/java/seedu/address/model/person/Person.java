package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.mod.Mod;
import seedu.address.model.skill.Skill;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Skill> skills = new HashSet<>();
    private final Set<Mod> mods = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Skill> skills, Set<Mod> mods) {
        requireAllNonNull(name, phone, email, address, skills, mods);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.skills.addAll(skills);
        this.mods.addAll(mods);
    }

    /**
     * Overloaded constructor to be remove once Mod is fully implemented.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Skill> skills) {
        requireAllNonNull(name, phone, email, address, skills);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.skills.addAll(skills);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable skill set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Skill> getSkills() {
        return Collections.unmodifiableSet(skills);
    }

    public Set<Mod> getMods() { return Collections.unmodifiableSet(mods);}

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getSkills().equals(getSkills());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, skills);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        Set<Skill> skills = getSkills();
        if (!skills.isEmpty()) {
            builder.append("; Skills: ");
            skills.forEach(builder::append);
        }

        return builder.toString();
    }

}
