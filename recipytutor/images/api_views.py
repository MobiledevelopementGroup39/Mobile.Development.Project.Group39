from rest_framework import viewsets
from rest_framework.permissions import IsAuthenticatedOrReadOnly
from .models import ImagePost, ImageComment
from .serializers import ImagePostSerializer, ImageCommentSerializer

class ImagePostViewSet(viewsets.ModelViewSet):
    queryset = ImagePost.objects.all()
    serializer_class = ImagePostSerializer
    permission_classes = [IsAuthenticatedOrReadOnly]

class ImageCommentViewSet(viewsets.ModelViewSet):
    queryset = ImageComment.objects.all()
    serializer_class = ImageCommentSerializer
    permission_classes = [IsAuthenticatedOrReadOnly]

    def perform_create(self, serializer):
        serializer.save(user=self.request.user)
