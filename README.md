This is an example of the weird bug with native-image.

MethodHandle of a getter of a `Long` with the expected value `null` returns `0` instead.

Execute: `mvn verify -Pnative -Dquarkus.native.additional-build-args=-Ob`

