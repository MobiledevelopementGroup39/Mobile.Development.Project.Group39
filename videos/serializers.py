from rest_framework import serializers
from .models import Video, VideoComment

class VideoSerializer(serializers.ModelSerializer):
    total_likes = serializers.ReadOnlyField()
    total_dislikes = serializers.ReadOnlyField()

    class Meta:
        model = Video
        fields = ['id', 'title', 'description', 'video_file', 'uploaded_by', 'uploaded_at', 'total_likes', 'total_dislikes']


class VideoCommentSerializer(serializers.ModelSerializer):
    class Meta:
        model = VideoComment
        fields = ['id', 'video', 'user', 'text', 'created_at']
