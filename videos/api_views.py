from rest_framework import viewsets
from rest_framework.permissions import IsAuthenticatedOrReadOnly
from rest_framework.response import Response
from django.shortcuts import get_object_or_404
from .models import Video, VideoComment
from .serializers import VideoSerializer, VideoCommentSerializer

class VideoViewSet(viewsets.ModelViewSet):
    queryset = Video.objects.all()
    serializer_class = VideoSerializer
    permission_classes = [IsAuthenticatedOrReadOnly]

class VideoCommentViewSet(viewsets.ModelViewSet):
    queryset = VideoComment.objects.all()
    serializer_class = VideoCommentSerializer
    permission_classes = [IsAuthenticatedOrReadOnly]

    def perform_create(self, serializer):
        serializer.save(user=self.request.user)
