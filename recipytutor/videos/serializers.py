from rest_framework import serializers
from .models import Video, VideoComment

class VideoSerializer(serializers.ModelSerializer):
    uploaded_by = serializers.CharField(source='uploaded_by.username', read_only=True)
    total_likes = serializers.ReadOnlyField()
    total_dislikes = serializers.ReadOnlyField()
    is_favorited = serializers.SerializerMethodField()

    class Meta:
        model = Video
        fields = [
            'id', 'title', 'description', 'video_file',
            'uploaded_by', 'uploaded_at', 'total_likes',
            'total_dislikes', 'thamnail', 'is_favorited'
        ]

    def get_is_favorited(self, obj):
        user = self.context.get('request').user
        return user.is_authenticated and user in obj.favorites.all()

class VideoCommentSerializer(serializers.ModelSerializer):
    user = serializers.CharField(source='user.username', read_only=True)

    class Meta:
        model = VideoComment
        fields = ['id', 'video', 'user', 'text', 'created_at']
