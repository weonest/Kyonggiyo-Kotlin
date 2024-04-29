package kyonggiyo.domain.user


class User(
        val id: Long,
        val role: Role,
        val nickname: String,
        val isDeleted: Boolean,
) {
    override fun equals(other: Any?): Boolean {
        if (other is User) {
            return this.id == other.id
        }
        return false
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}

//@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//    @Column(unique = true)
//    private String nickname;
//
//    private boolean isDeleted;
//
//    public User(String nickname) {
//        this.role = Role.USER;
//        this.nickname = nickname;
//        this.isDeleted = false;
//    }