package com.mycom.myapp.exception;

public class UserException extends RuntimeException {
    public static class UserNotFoundException extends BusinessException {
        public UserNotFoundException(String email) {
            super("USER_NOT_FOUND", String.format("Email %s인 회원을 찾을 수 없습니다.", email));
        }

        public UserNotFoundException(int id) {
            super("USER_NOT_FOUND", String.format("ID가 %d인 회원을 찾을 수 없습니다.", id));
        }
    }

    public static class UserExistException extends BusinessException {
        public UserExistException(String email) {
            super("USER_EXIST", String.format("Email %s인 회원이 이미 존재합니다..", email));
        }
    }

    public static class UserCreationFailedException  extends BusinessException {
        public UserCreationFailedException () {
            super("USER_CREATION_FAILED", "회원 생성에 실패했습니다.");
        }
    }

    public static class InvalidPasswordException extends BusinessException {
        public InvalidPasswordException() {
            super("USER_INVALID_PASSWORD", "비밀번호가 일치하지 않습니다.");
        }
    }

}