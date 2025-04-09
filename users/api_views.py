from rest_framework import generics, permissions
from .models import MyUser
from .serializers import UserProfileSerializer

class UserProfileUpdateView(generics.RetrieveUpdateAPIView):
    queryset = MyUser.objects.all()
    serializer_class = UserProfileSerializer
    permission_classes = [permissions.IsAuthenticated]

    def get_object(self):
        return self.request.user  # Returns the logged-in user
