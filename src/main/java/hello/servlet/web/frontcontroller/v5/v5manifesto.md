this v5 is present to incorporate cross version compatibility controllers within v3/v4, via an adapter.

key point is to show how the adapter allows for the frontControllerServlet to only change minimally(
-> only environment parameters -> adding the adapters into map changes.
note that this can be extracted out to a different class to facilitate DI, which in turn preserves OCP for the FrontController class
)


