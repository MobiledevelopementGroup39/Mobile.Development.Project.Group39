from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .api_views import VideoViewSet, VideoCommentViewSet

router = DefaultRouter()
router.register(r'upload_videos', VideoViewSet, basename='video')
router.register(r'video-comments', VideoCommentViewSet, basename='video-comment')

urlpatterns = [
    path('', include(router.urls)),
]
